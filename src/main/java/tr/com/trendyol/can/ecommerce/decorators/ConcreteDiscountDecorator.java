package tr.com.trendyol.can.ecommerce.decorators;

import lombok.NoArgsConstructor;
import tr.com.trendyol.can.ecommerce.services.dto.DiscountDecoratorDTO;

@NoArgsConstructor
class ConcreteDiscountDecorator implements DiscountDecorator {

    private DiscountDecorator discountDecorator;

    ConcreteDiscountDecorator(DiscountDecorator discountDecorator) {
        this.discountDecorator = discountDecorator;
    }

    @Override
    public DiscountDecoratorDTO apply(DiscountDecoratorDTO decoratable) {
        return discountDecorator.apply(decoratable);
    }
}
