package tr.com.trendyol.can.ecommerce.decorators;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tr.com.trendyol.can.ecommerce.services.dto.DiscountDecoratorDTO;
import tr.com.trendyol.can.ecommerce.util.TestUtil;

import static org.junit.jupiter.api.Assertions.*;

class DiscountCouponRateOf10Test {

    private DiscountCouponRateOf10 discountCouponRateOf10;

    @BeforeEach
    private void init() {
        DiscountRateOf50 discountRateOf50 = new DiscountRateOf50();
        DiscountRateOf20 discountRateOf20 = new DiscountRateOf20(discountRateOf50);
        DiscountOfSameDetails discountOfSameDetails = new DiscountOfSameDetails(discountRateOf20);
        discountCouponRateOf10 = new DiscountCouponRateOf10(discountOfSameDetails);
    }

    @Test
    void apply() {
        DiscountDecoratorDTO discountDecoratorDTO = TestUtil.initializeDiscountDecoratorDTO(3L);
        DiscountDecoratorDTO _discountDecoratorDTO = discountCouponRateOf10.apply(discountDecoratorDTO);
        assertNotNull(_discountDecoratorDTO);
        assertEquals(60.0, _discountDecoratorDTO.getCouponDiscountAmount());
        DiscountDecoratorDTO discountDecoratorDTO1 = TestUtil.initializeDiscountDecoratorDTO(1L);
        assertNotNull(discountDecoratorDTO1);
        assertEquals(0.0, discountDecoratorDTO1.getCouponDiscountAmount());

    }
}