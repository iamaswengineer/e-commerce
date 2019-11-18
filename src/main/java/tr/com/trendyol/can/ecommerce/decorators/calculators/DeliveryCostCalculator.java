package tr.com.trendyol.can.ecommerce.decorators.calculators;

import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@NoArgsConstructor
public class DeliveryCostCalculator {

    @Value("${discountcalculator.costPerDelivery}")
    private Double costPerDelivery;

    @Value("${discountcalculator.fixedCost}")
    private Double fixedCost;

    @Value("${discountcalculator.costPerProduct}")
    private Double costPerProduct;

    public Double calculateDeliveryCost(Long numberOfDistinctCategories, Long numberOfDistinctProducts){
        return (costPerDelivery * numberOfDistinctCategories) + (costPerProduct * numberOfDistinctProducts) + fixedCost;
    }
}
