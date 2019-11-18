package tr.com.trendyol.can.ecommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import tr.com.trendyol.can.ecommerce.entities.ShoppingCart;

public interface ShoppingCartRepository extends JpaRepository <ShoppingCart, Long> {

    ShoppingCart findShoppingCartByUserId(Long id);

    @Query(nativeQuery = true, value = "select c.cart_price_after_discount from shopping_cart c where c.user_id = :userId")
    Double fetchCartPriceAfterDiscount(@Param("userId") Long userId);

    @Query(nativeQuery = true, value = "select c.coupon_discount_amount from shopping_cart c where c.user_id = :userId")
    Double fetchCouponDiscount(Long userId);

    @Query(nativeQuery = true, value = "select c.campaign_discount_amount from shopping_cart c where c.user_id = :userId")
    Double fetchCampaignDiscount(Long userId);

    @Query(nativeQuery = true, value = "select count(scd.product_id) from shopping_cart sc join shopping_cart_detail scd on sc.id = scd.shopping_cart_id where sc.user_id = :userId")
    Long fetchNumberOfDistinctProducts(Long userId);

    @Query(nativeQuery = true, value = "select count(distinct p.category_id) from shopping_cart sc join shopping_cart_detail scd on sc.id = scd.shopping_cart_id join product p on scd.product_id = p.id join category c on p.category_id = c.id  where sc.user_id = :userId")
    Long fetchNumberOfDistinctCategories(Long userId);
}
