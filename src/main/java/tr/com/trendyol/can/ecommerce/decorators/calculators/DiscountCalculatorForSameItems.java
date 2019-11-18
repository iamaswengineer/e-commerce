package tr.com.trendyol.can.ecommerce.decorators.calculators;


import org.springframework.stereotype.Component;
import tr.com.trendyol.can.ecommerce.services.dto.DiscountServiceDTO;
import tr.com.trendyol.can.ecommerce.services.dto.ShoppingCartDetailServiceDTO;

@Component
public class DiscountCalculatorForSameItems {

    public Double calculate(DiscountServiceDTO discount, ShoppingCartDetailServiceDTO shoppingCartDetailServiceDTO) {

        double percentage = (100.0 * discount.getAmount()) / shoppingCartDetailServiceDTO.getTotalPrice();
        return (percentage * shoppingCartDetailServiceDTO.getTotalPrice()) / 100;


    }
}
