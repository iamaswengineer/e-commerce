package tr.com.trendyol.can.ecommerce.decorators;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tr.com.trendyol.can.ecommerce.services.dto.DiscountDecoratorDTO;
import tr.com.trendyol.can.ecommerce.util.TestUtil;


import static org.junit.jupiter.api.Assertions.*;

class DiscountRateOf20Test {

    private DiscountRateOf50 discountRateOf50;
    private DiscountRateOf20 discountRateOf20;

    @BeforeEach
    private void init() {
        discountRateOf50 = new DiscountRateOf50();
        discountRateOf20 = new DiscountRateOf20(discountRateOf50);
    }

    @Test
    void apply() {
        DiscountDecoratorDTO discountDecoratorDTO = TestUtil.initializeDiscountDecoratorDTO(4L);
        DiscountDecoratorDTO _discountDecoratorDTO = discountRateOf20.apply(discountDecoratorDTO);
        assertNotNull(_discountDecoratorDTO);
        assertEquals(160.0, _discountDecoratorDTO.getCampaignDiscountAmount());
        DiscountDecoratorDTO discountDecoratorDTO1 = TestUtil.initializeDiscountDecoratorDTO(1L);
        assertNotNull(discountDecoratorDTO1);
        assertEquals(0.0, discountDecoratorDTO1.getCampaignDiscountAmount());

    }
}