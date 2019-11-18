package tr.com.trendyol.can.ecommerce.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tr.com.trendyol.can.ecommerce.decorators.*;
import tr.com.trendyol.can.ecommerce.decorators.calculators.DeliveryCostCalculator;
import tr.com.trendyol.can.ecommerce.conf.ApplicationContextProvider;
import tr.com.trendyol.can.ecommerce.controllers.dtos.ShoppingCartDTO;
import tr.com.trendyol.can.ecommerce.entities.Product;
import tr.com.trendyol.can.ecommerce.entities.ShoppingCart;
import tr.com.trendyol.can.ecommerce.entities.ShoppingCartDetail;
import tr.com.trendyol.can.ecommerce.entities.User;
import tr.com.trendyol.can.ecommerce.exceptions.ProductNotFoundException;
import tr.com.trendyol.can.ecommerce.exceptions.ShoppingCartNotFoundException;
import tr.com.trendyol.can.ecommerce.repositories.ShoppingCartRepository;
import tr.com.trendyol.can.ecommerce.repositories.ProductRepository;
import tr.com.trendyol.can.ecommerce.repositories.UserRepository;
import tr.com.trendyol.can.ecommerce.services.cartcache.ShoppingCartCache;
import tr.com.trendyol.can.ecommerce.services.cartutils.CartUtils;
import tr.com.trendyol.can.ecommerce.services.dto.DiscountDecoratorDTO;
import tr.com.trendyol.can.ecommerce.services.dto.ShoppingCartDetailServiceDTO;

import java.util.List;


@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {

    private ProductRepository productRepository;
    private DiscountManager discountManager;
    private ShoppingCartRepository shoppingCartRepository;
    private UserRepository userRepository;
    private ApplicationContextProvider applicationContextProvider;
    private ShoppingCartCache shoppingCartCache;
    private DeliveryCostCalculator deliveryCostCalculator;

    @Autowired
    public ShoppingCartServiceImpl(ProductRepository productRepository, DiscountManager discountManager, ShoppingCartRepository shoppingCartRepository, UserRepository userRepository, ApplicationContextProvider applicationContextProvider, DeliveryCostCalculator deliveryCostCalculator) {
        this.productRepository = productRepository;
        this.shoppingCartRepository = shoppingCartRepository;
        this.userRepository = userRepository;
        this.applicationContextProvider = applicationContextProvider;
        this.discountManager = discountManager;
        this.shoppingCartCache = (ShoppingCartCache) applicationContextProvider.getContext().getBean("shoppingCartCache");
        this.deliveryCostCalculator = deliveryCostCalculator;
    }

    @Override
    public Product saveOne(ShoppingCartDTO shoppingCartDTO) {
        if (!productRepository.existsById(shoppingCartDTO.getProductId())) {
            throw new ProductNotFoundException("The product you add is not found.");
        }
        DiscountDecoratorDTO discountDecoratorDTO;
        Product product = productRepository.findBy(shoppingCartDTO.getProductId());
        User user = userRepository.findUserById(shoppingCartDTO.getUserId());
        ShoppingCart shoppingCart = shoppingCartRepository.findShoppingCartByUserId(shoppingCartDTO.getUserId());
        List<ShoppingCartDetailServiceDTO> detailListOfUser = shoppingCartCache.findByUserId(shoppingCartDTO.getUserId());
        if (CartUtils.isProductExist(shoppingCartDTO.getProductId(), detailListOfUser)) {
            ShoppingCartDetailServiceDTO detail = CartUtils.incrementQuantityIfSameProductExist(shoppingCartDTO.getProductId(), shoppingCartDTO.getQuantity(), detailListOfUser);
            cacheUpdate(user, detailListOfUser);
            discountDecoratorDTO = applyDiscount(detail);
            ShoppingCart _shoppingCart = CartUtils.mapToShoppingCart(user, shoppingCart, discountDecoratorDTO);
            for (ShoppingCartDetail shoppingCartDetail : _shoppingCart.getShoppingCartDetailList()) {
                if (shoppingCartDetail.getProduct().getId().equals(shoppingCartDTO.getProductId())) {
                    shoppingCartDetail.setQuantity(shoppingCartDetail.getQuantity() + shoppingCartDTO.getQuantity());
                }
            }
            shoppingCartRepository.save(_shoppingCart);
        } else {
            ShoppingCartDetailServiceDTO shoppingCartDetailServiceDTO = CartUtils.mapToShoppingCartDetailServiceDTO(shoppingCartDTO);
            shoppingCartDetailServiceDTO.setCategoryId(product.getCategory().getId());
            shoppingCartDetailServiceDTO.setPrice(product.getPrice());
            shoppingCartDetailServiceDTO.setTotalPrice(shoppingCartDetailServiceDTO.getPrice() * shoppingCartDetailServiceDTO.getQuantity());

            detailListOfUser.add(shoppingCartDetailServiceDTO);
            cacheUpdate(user, detailListOfUser);

            discountDecoratorDTO = applyDiscount(shoppingCartDetailServiceDTO);

            ShoppingCartDetail shoppingCartDetail = CartUtils.mapToShoppingCartDetail(shoppingCartDetailServiceDTO, product);
            ShoppingCart _shoppingCart = CartUtils.mapToShoppingCart(user, discountDecoratorDTO, shoppingCart, shoppingCartDetail);
            shoppingCartDetail.setShoppingCart(_shoppingCart);
            shoppingCartRepository.save(_shoppingCart);
        }

        return product;
    }

    @Override
    public Double fetchCartPriceAfterDiscount(Long userId) {
        return shoppingCartRepository.fetchCartPriceAfterDiscount(userId);
    }

    @Override
    public Double fetchCouponDiscount(Long userId) {
        if(shoppingCartRepository.fetchCouponDiscount(userId) == null){
            throw new ShoppingCartNotFoundException("Coupon discount does not calculated.There is not shopping cart instance for this user.");
        }
        return shoppingCartRepository.fetchCouponDiscount(userId);
    }

    @Override
    public Double fetchCampaignDiscount(Long userId) {
        if(shoppingCartRepository.fetchCampaignDiscount(userId) == null){
            throw new ShoppingCartNotFoundException("Campaign discount does not calculated.There is not shopping cart instance for this user.");
        }
        return shoppingCartRepository.fetchCampaignDiscount(userId);
    }

    @Override
    public Double getDeliveryCost(Long userId) {
        Long numberOfDistinctCategories = shoppingCartRepository.fetchNumberOfDistinctCategories(userId);
        Long numberOfDistinctProducts = shoppingCartRepository.fetchNumberOfDistinctProducts(userId);
        if(numberOfDistinctCategories < 1 && numberOfDistinctProducts < 1){
            throw new ShoppingCartNotFoundException("There is not shopping cart instance for this user.");
        }
        return deliveryCostCalculator.calculateDeliveryCost(numberOfDistinctCategories, numberOfDistinctProducts);
    }

    private void cacheUpdate(User user, List<ShoppingCartDetailServiceDTO> detailListOfUser) {
        shoppingCartCache.update(user.getId(), detailListOfUser);
        shoppingCartCache.constructCategoryShoppingCartDetailMap(user.getId());
        shoppingCartCache.constructByShoppingCartDetailQuantityMap(user.getId());
    }

    private DiscountDecoratorDTO applyDiscount(ShoppingCartDetailServiceDTO shoppingCartDetailServiceDTO) {
        DiscountDecoratorDTO discountDecoratorDTO = new DiscountDecoratorDTO();
        discountDecoratorDTO.setDetailMapByCategory(shoppingCartCache.getDetailMapByCategory());
        discountDecoratorDTO.setQuantityMapByDetail(shoppingCartCache.getQuantityMapByDetail());
        discountDecoratorDTO.setTotalCartPrice(shoppingCartDetailServiceDTO.getTotalPrice());
        discountDecoratorDTO = discountManager.apply(discountDecoratorDTO);
        discountDecoratorDTO.setCartPriceAfterDiscount(discountDecoratorDTO.getTotalCartPrice() - (discountDecoratorDTO.getCampaignDiscountAmount() + discountDecoratorDTO.getCouponDiscountAmount()));
        return discountDecoratorDTO;
    }

}
