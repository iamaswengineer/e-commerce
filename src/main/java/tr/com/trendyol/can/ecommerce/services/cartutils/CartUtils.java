package tr.com.trendyol.can.ecommerce.services.cartutils;

import tr.com.trendyol.can.ecommerce.controllers.dtos.ShoppingCartDTO;
import tr.com.trendyol.can.ecommerce.entities.Product;
import tr.com.trendyol.can.ecommerce.entities.ShoppingCart;
import tr.com.trendyol.can.ecommerce.entities.ShoppingCartDetail;
import tr.com.trendyol.can.ecommerce.entities.User;
import tr.com.trendyol.can.ecommerce.services.dto.DiscountDecoratorDTO;
import tr.com.trendyol.can.ecommerce.services.dto.ShoppingCartDetailServiceDTO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CartUtils {

    public static ShoppingCartDetailServiceDTO incrementQuantityIfSameProductExist(Long productId, Long quantity, List<ShoppingCartDetailServiceDTO> shoppingCartDetailServiceDTOList) {
        for(ShoppingCartDetailServiceDTO shoppingCartDetailServiceDTO : shoppingCartDetailServiceDTOList){
            if(shoppingCartDetailServiceDTO.getProductId().equals(productId)){
                shoppingCartDetailServiceDTO.incrementQuantity(quantity);
                return shoppingCartDetailServiceDTO;
            }
        }
        return null;
    }

    public static boolean isProductExist(Long productId, List<ShoppingCartDetailServiceDTO> shoppingCartDetailServiceDTOList) {
        if(shoppingCartDetailServiceDTOList.size() == 0){
            return false;
        }
        for(ShoppingCartDetailServiceDTO shoppingCartDetailServiceDTO : shoppingCartDetailServiceDTOList){
            if(shoppingCartDetailServiceDTO.getProductId().equals(productId)){
                return true;
            }
        }
        return false;
    }

    public static ShoppingCart mapToShoppingCart(User user, ShoppingCart shoppingCart, DiscountDecoratorDTO discountDecoratorDTO){
        shoppingCart.setCouponDiscountAmount(shoppingCart.getCouponDiscountAmount() != null ? shoppingCart.getCouponDiscountAmount() + discountDecoratorDTO.getCouponDiscountAmount() : discountDecoratorDTO.getCouponDiscountAmount());
        shoppingCart.setCampaignDiscountAmount(shoppingCart.getCampaignDiscountAmount() != null ? shoppingCart.getCampaignDiscountAmount() + discountDecoratorDTO.getCampaignDiscountAmount() : discountDecoratorDTO.getCampaignDiscountAmount());
        shoppingCart.setTotalCartPrice(shoppingCart.getTotalCartPrice() != null ? shoppingCart.getTotalCartPrice() + discountDecoratorDTO.getTotalCartPrice() : discountDecoratorDTO.getTotalCartPrice());
        shoppingCart.setCartPriceAfterDiscount(shoppingCart.getTotalCartPrice() - (shoppingCart.getCouponDiscountAmount() + shoppingCart.getCampaignDiscountAmount()));
        shoppingCart.setUser(user);
        return shoppingCart;
    }

    public static ShoppingCartDetailServiceDTO mapToShoppingCartDetailServiceDTO(ShoppingCartDTO shoppingCartDTO){
        ShoppingCartDetailServiceDTO shoppingCartDetailServiceDTO = new ShoppingCartDetailServiceDTO();
        shoppingCartDetailServiceDTO.setProductId(shoppingCartDTO.getProductId());
        shoppingCartDetailServiceDTO.setQuantity(shoppingCartDTO.getQuantity());
        return shoppingCartDetailServiceDTO;
    }

    public static ShoppingCartDetail mapToShoppingCartDetail(ShoppingCartDetailServiceDTO shoppingCartDetailServiceDTO, Product product){
        ShoppingCartDetail shoppingCartDetail = new ShoppingCartDetail();
        shoppingCartDetail.setQuantity(shoppingCartDetailServiceDTO.getQuantity());
        shoppingCartDetail.setTotalPrice(shoppingCartDetailServiceDTO.getTotalPrice());
        shoppingCartDetail.setProduct(product);
        return shoppingCartDetail;
    }

    public static ShoppingCart mapToShoppingCart(User user, DiscountDecoratorDTO discountDecoratorDTO, ShoppingCart shoppingCart, ShoppingCartDetail shoppingCartDetail){
        if(shoppingCart == null){
            shoppingCart = new ShoppingCart();
        }
        shoppingCart.setUser(user);
        shoppingCart.getShoppingCartDetailList().add(shoppingCartDetail);
        shoppingCart.setTotalCartPrice(shoppingCart.getTotalCartPrice() != null ? shoppingCart.getTotalCartPrice() + discountDecoratorDTO.getTotalCartPrice() : discountDecoratorDTO.getTotalCartPrice());
        shoppingCart.setCampaignDiscountAmount(shoppingCart.getCampaignDiscountAmount() != null ? shoppingCart.getCampaignDiscountAmount() + discountDecoratorDTO.getCampaignDiscountAmount() : discountDecoratorDTO.getCampaignDiscountAmount());
        shoppingCart.setCouponDiscountAmount(shoppingCart.getCouponDiscountAmount() != null ? shoppingCart.getCouponDiscountAmount() + discountDecoratorDTO.getCouponDiscountAmount() : discountDecoratorDTO.getCouponDiscountAmount());
        shoppingCart.setCartPriceAfterDiscount(shoppingCart.getTotalCartPrice() - (shoppingCart.getCouponDiscountAmount() + shoppingCart.getCampaignDiscountAmount()));

        return shoppingCart;
    }
}
