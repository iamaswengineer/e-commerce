package tr.com.trendyol.can.ecommerce.decorators;

import org.springframework.stereotype.Component;
import tr.com.trendyol.can.ecommerce.services.dto.DiscountDecoratorDTO;

@Component
public class DiscountManager {

    private DiscountCouponRateOf10 discountCouponRateOf10;
    private DiscountOfSameDetails discountOfSameDetails;
    private DiscountRateOf20 discountRateOf20;
    private DiscountRateOf50 discountRateOf50;

    public DiscountDecoratorDTO apply(DiscountDecoratorDTO decoratorDTO){
        discountRateOf50 = new DiscountRateOf50();
        discountRateOf20 = new DiscountRateOf20(discountRateOf50);
        discountOfSameDetails = new DiscountOfSameDetails(discountRateOf20);
        this.discountCouponRateOf10 = new DiscountCouponRateOf10(discountOfSameDetails);
        return discountCouponRateOf10.apply(decoratorDTO);
    }
}
