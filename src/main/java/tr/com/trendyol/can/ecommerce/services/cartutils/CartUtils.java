package tr.com.trendyol.can.ecommerce.services.cartutils;

import tr.com.trendyol.can.ecommerce.controllers.dtos.ShoppingCartDTO;
import tr.com.trendyol.can.ecommerce.entities.Product;
import tr.com.trendyol.can.ecommerce.entities.ShoppingCart;
import tr.com.trendyol.can.ecommerce.entities.ShoppingCartDetail;
import tr.com.trendyol.can.ecommerce.entities.User;
import tr.com.trendyol.can.ecommerce.services.dto.DiscountDecoratorDTO;
import tr.com.trendyol.can.ecommerce.services.dto.ShoppingCartDetailServiceDTO;

import java.util.*;

public class CartUtils {

    public static ShoppingCartDetailServiceDTO returnExistingItem(Long productId, Stack<ShoppingCartDetailServiceDTO> detailStack) {
        if(detailStack.size() == 0){
            return null;
        }

        for(ShoppingCartDetailServiceDTO shoppingCartDetailServiceDTO : detailStack){
            if(shoppingCartDetailServiceDTO.getProductId().equals(productId)){
                return shoppingCartDetailServiceDTO;
            }
        }
        return null;
    }

    public static void mapToShoppingCart(User user, ShoppingCart shoppingCart, DiscountDecoratorDTO discountDecoratorDTO){
        shoppingCart.setCouponDiscountAmount(discountDecoratorDTO.getCouponDiscountAmount());
        shoppingCart.setCampaignDiscountAmount(shoppingCart.getCampaignDiscountAmount() != null ? shoppingCart.getCampaignDiscountAmount() + discountDecoratorDTO.getCampaignDiscountAmount() : discountDecoratorDTO.getCampaignDiscountAmount());
        shoppingCart.setTotalCartPrice(discountDecoratorDTO.getTotalCartPrice());
        shoppingCart.setCartPriceAfterDiscount(shoppingCart.getTotalCartPrice() - (shoppingCart.getCouponDiscountAmount() + shoppingCart.getCampaignDiscountAmount()));
        shoppingCart.setUser(user);
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
}
