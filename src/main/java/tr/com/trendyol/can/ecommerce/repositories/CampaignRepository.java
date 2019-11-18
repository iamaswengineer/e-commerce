package tr.com.trendyol.can.ecommerce.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import tr.com.trendyol.can.ecommerce.entities.Campaign;
import tr.com.trendyol.can.ecommerce.entities.enums.DiscountStrategy;

public interface CampaignRepository extends DiscountRepository<Campaign>{

    @Query(nativeQuery = true, value = "select d.* from Discount d where d.discount_strategy = :discountStrategy and d.discount_type = :discType and d.amount = :amount")
    Campaign findByDiscountStrategyAndDiscountTypeIdAndAmount(@Param("discountStrategy") Long discountStrategy, @Param("discType")String discType, @Param("amount") Double amount);
}
