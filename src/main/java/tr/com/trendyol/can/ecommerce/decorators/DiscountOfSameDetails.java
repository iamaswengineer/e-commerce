package tr.com.trendyol.can.ecommerce.decorators;

import tr.com.trendyol.can.ecommerce.decorators.calculators.DiscountCalculatorForSameItems;
import tr.com.trendyol.can.ecommerce.entities.enums.DiscountStrategy;
import tr.com.trendyol.can.ecommerce.services.dto.DiscountDecoratorDTO;
import tr.com.trendyol.can.ecommerce.services.dto.DiscountServiceDTO;
import tr.com.trendyol.can.ecommerce.services.dto.ShoppingCartDetailServiceDTO;

import java.util.Map;

class DiscountOfSameDetails extends ConcreteDiscountDecorator {

    private DiscountCalculatorForSameItems discountCalculatorForSameItems;

    DiscountOfSameDetails(DiscountDecorator discountDecorator) {
        super(discountDecorator);
        this.discountCalculatorForSameItems = new DiscountCalculatorForSameItems();
    }

    @Override
    public DiscountDecoratorDTO apply(DiscountDecoratorDTO decoratable) {
        DiscountDecoratorDTO decorated = super.apply(decoratable);
        for (Map.Entry<ShoppingCartDetailServiceDTO, Long> entry : decorated.getQuantityMapByDetail().entrySet()) {
            if (entry.getValue() > 1) {
                DiscountServiceDTO d =
                        new DiscountServiceDTO(
                                entry.getKey().getCategoryId(),
                                5.0,
                                entry.getValue(),
                                DiscountStrategy.AMOUNT.getValue()
                        );

                Double calculated = discountCalculatorForSameItems.calculate(d, entry.getKey());
                decoratable.setCampaignDiscountAmount(decoratable.getCampaignDiscountAmount() + calculated);
                return decoratable;
            }
        }
        return decoratable;
    }
}
