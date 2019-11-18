package tr.com.trendyol.can.ecommerce.decorators;

import tr.com.trendyol.can.ecommerce.decorators.calculators.DiscountCalculator;
import tr.com.trendyol.can.ecommerce.entities.enums.DiscountStrategy;
import tr.com.trendyol.can.ecommerce.services.dto.DiscountDecoratorDTO;
import tr.com.trendyol.can.ecommerce.services.dto.DiscountServiceDTO;
import tr.com.trendyol.can.ecommerce.services.dto.ShoppingCartDetailServiceDTO;

import java.util.ArrayList;
import java.util.Map;
import java.util.Set;


class DiscountRateOf20 extends ConcreteDiscountDecorator {

    private DiscountCalculator discountCalculator;

    DiscountRateOf20(DiscountDecorator discountDecorator) {
        super(discountDecorator);
        this.discountCalculator = new DiscountCalculator();
    }


    @Override
    public DiscountDecoratorDTO apply(DiscountDecoratorDTO decoratable) {
        DiscountDecoratorDTO decorated = super.apply(decoratable);

        for(Map.Entry<Long, Set<ShoppingCartDetailServiceDTO>> entry : decorated.getDetailMapByCategory().entrySet()){
            if (entry.getValue().size() > 3) {
                DiscountServiceDTO d =
                        new DiscountServiceDTO(
                                new ArrayList<>(entry.getValue()).get(0).getCategoryId(),
                                20.0,
                                (long) entry.getValue().size(),
                                DiscountStrategy.RATE.getValue());

                Double calculated = discountCalculator.calculate(d, entry.getValue());
                decoratable.setCampaignDiscountAmount(decoratable.getCampaignDiscountAmount() + calculated);
                return decoratable;
            }
        }
        return decoratable;
    }
}
