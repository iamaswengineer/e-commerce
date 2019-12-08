package tr.com.trendyol.can.ecommerce.services.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.*;

@Getter
@Setter
public class DiscountDecoratorDTO {
    private Map<Long, List<ShoppingCartDetailServiceDTO>> detailMapByCategory;
    private Stack<ShoppingCartDetailServiceDTO> shopingCartDetails;
    private Double totalCartPrice = 0.0;
    private Double cartPriceAfterDiscount = 0.0;
    private Double couponDiscountAmount = 0.0;
    private Double campaignDiscountAmount = 0.0;
}
