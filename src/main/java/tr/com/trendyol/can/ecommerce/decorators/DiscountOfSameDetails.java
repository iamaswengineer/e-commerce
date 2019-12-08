package tr.com.trendyol.can.ecommerce.decorators;

import tr.com.trendyol.can.ecommerce.decorators.calculators.DiscountCalculatorForSameItems;
import tr.com.trendyol.can.ecommerce.entities.enums.DiscountStrategy;
import tr.com.trendyol.can.ecommerce.services.dto.DiscountDecoratorDTO;
import tr.com.trendyol.can.ecommerce.services.dto.DiscountServiceDTO;
import tr.com.trendyol.can.ecommerce.services.dto.ShoppingCartDetailServiceDTO;


class DiscountOfSameDetails extends ConcreteDiscountDecorator {

    private DiscountCalculatorForSameItems discountCalculatorForSameItems;

    DiscountOfSameDetails(DiscountDecorator discountDecorator) {
        super(discountDecorator);
        this.discountCalculatorForSameItems = new DiscountCalculatorForSameItems();
    }

    @Override
    public DiscountDecoratorDTO apply(DiscountDecoratorDTO decoratable) {
        DiscountDecoratorDTO decorated = super.apply(decoratable);
        for (ShoppingCartDetailServiceDTO detail : decorated.getShopingCartDetails()) {
            if (detail.getQuantity() > 1) {
                DiscountServiceDTO d =
                        new DiscountServiceDTO(
                                detail.getCategoryId(),
                                5.0,
                                detail.getQuantity(),
                                DiscountStrategy.AMOUNT.getValue()
                        );

                Double calculated = discountCalculatorForSameItems.calculate(d, detail);
                decoratable.setCampaignDiscountAmount(decoratable.getCampaignDiscountAmount() + calculated);
            }
        }
        return decoratable;
    }
}
