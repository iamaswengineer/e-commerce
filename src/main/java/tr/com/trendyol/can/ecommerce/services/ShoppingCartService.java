package tr.com.trendyol.can.ecommerce.services;

import tr.com.trendyol.can.ecommerce.controllers.dtos.ShoppingCartDTO;
import tr.com.trendyol.can.ecommerce.entities.Product;

public interface ShoppingCartService {

    Product saveOne(ShoppingCartDTO shoppingCartDTO);
    Double fetchCartPriceAfterDiscount(Long userId);
    Double fetchCouponDiscount(Long userId);
    Double fetchCampaignDiscount(Long userId);
    Double getDeliveryCost(Long userId);
}
