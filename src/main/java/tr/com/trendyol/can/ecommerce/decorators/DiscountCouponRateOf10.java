package tr.com.trendyol.can.ecommerce.decorators;

import tr.com.trendyol.can.ecommerce.decorators.calculators.DiscountCalculator;
import tr.com.trendyol.can.ecommerce.entities.enums.DiscountStrategy;
import tr.com.trendyol.can.ecommerce.services.dto.DiscountDecoratorDTO;
import tr.com.trendyol.can.ecommerce.services.dto.DiscountServiceDTO;
import tr.com.trendyol.can.ecommerce.services.dto.ShoppingCartDetailServiceDTO;

import java.util.Map;


class DiscountCouponRateOf10 extends ConcreteDiscountDecorator {

    private Double minimumPurchaseAmount = 100.0;
    private DiscountCalculator discountCalculator;

    DiscountCouponRateOf10(DiscountDecorator discountDecorator) {
        super(discountDecorator);
        this.discountCalculator = new DiscountCalculator();
    }

    @Override
    public DiscountDecoratorDTO apply(DiscountDecoratorDTO decoratable) {
        DiscountDecoratorDTO decorated = super.apply(decoratable);
        if (decoratable.getTotalCartPrice() - decorated.getCampaignDiscountAmount() > minimumPurchaseAmount) {
            DiscountServiceDTO d =
                    new DiscountServiceDTO(
                            10.0,
                            DiscountStrategy.RATE.getValue());
            Double calculated = discountCalculator.calculateCouponDiscount(d, decoratable);
            decoratable.setCouponDiscountAmount(decoratable.getCouponDiscountAmount() + calculated);

        }

        return decoratable;
    }
}
