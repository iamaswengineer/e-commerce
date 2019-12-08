package tr.com.trendyol.can.ecommerce.services.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Getter
@Setter
public class DiscountDecoratorDTO {
    private Map<Long, Set<ShoppingCartDetailServiceDTO>> detailMapByCategory;
    private Map<ShoppingCartDetailServiceDTO, Long> quantityMapByDetail;
    private Double totalCartPrice = 0.0;
    private Double cartPriceAfterDiscount = 0.0;
    private Double couponDiscountAmount = 0.0;
    private Double campaignDiscountAmount = 0.0;
}
