package tr.com.trendyol.can.ecommerce.decorators.calculators;

import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import tr.com.trendyol.can.ecommerce.services.dto.DiscountDecoratorDTO;
import tr.com.trendyol.can.ecommerce.services.dto.DiscountServiceDTO;
import tr.com.trendyol.can.ecommerce.services.dto.ShoppingCartDetailServiceDTO;

import java.util.List;
import java.util.Set;

@NoArgsConstructor
@Component
public class DiscountCalculator {

    @Value("${shoppingcart.minimum.purchase.amount}")
    private Double minimumPurchaseAmount;

    public Double calculate(DiscountServiceDTO discount, List<ShoppingCartDetailServiceDTO> shoppingCartDetailServiceDTOList) {

        double result = 0.0;
        for(ShoppingCartDetailServiceDTO s : shoppingCartDetailServiceDTOList){
            result = result + (s.getTotalPrice() * discount.getAmount() / 100);
        }
        return result;
    }
    public Double calculateCouponDiscount(DiscountServiceDTO discount, DiscountDecoratorDTO decoratable) {
        double result = 0.0;
        result = result + (decoratable.getTotalCartPrice() * discount.getAmount() / 100);
        return result;
    }
}
