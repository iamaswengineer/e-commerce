package tr.com.trendyol.can.ecommerce.decorators;


import tr.com.trendyol.can.ecommerce.decorators.calculators.DiscountCalculator;
import tr.com.trendyol.can.ecommerce.entities.enums.DiscountStrategy;
import tr.com.trendyol.can.ecommerce.services.dto.DiscountDecoratorDTO;
import tr.com.trendyol.can.ecommerce.services.dto.DiscountServiceDTO;
import tr.com.trendyol.can.ecommerce.services.dto.ShoppingCartDetailServiceDTO;


import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

class DiscountRateOf50 implements DiscountDecorator {

    private DiscountCalculator discountCalculator;

    public DiscountRateOf50() {
        this.discountCalculator = new DiscountCalculator();
    }

    @Override
    public DiscountDecoratorDTO apply(DiscountDecoratorDTO decoratable) {
        for(Map.Entry<Long, Set<ShoppingCartDetailServiceDTO>> entry : decoratable.getDetailMapByCategory().entrySet()){
            if (entry.getValue().size() > 5) {
                DiscountServiceDTO d =
                        new DiscountServiceDTO(
                                new ArrayList<>(entry.getValue()).get(0).getCategoryId(),
                                50.0,
                                (long) entry.getValue().size(),
                                DiscountStrategy.RATE.getValue());

                Double calculated = discountCalculator.calculate(d, entry.getValue());
                decoratable.setCampaignDiscountAmount(calculated);
                return decoratable;
            }
        }
        return decoratable;
    }
}
