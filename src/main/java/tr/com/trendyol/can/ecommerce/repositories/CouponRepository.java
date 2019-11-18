package tr.com.trendyol.can.ecommerce.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import tr.com.trendyol.can.ecommerce.entities.Campaign;
import tr.com.trendyol.can.ecommerce.entities.Coupon;

public interface CouponRepository extends DiscountRepository<Coupon>{

    @Query(nativeQuery = true,value="select c.* from Discount c where c.discount_strategy = :discountStrategy and c.discount_type = :discType and c.amount = :amount")
    Campaign findByDiscountStrategyAndDiscountTypeIdAndAmount(@Param("discountStrategy") Long discountStrategy, @Param("discType") String discType,  @Param("amount")Double amount);
}
