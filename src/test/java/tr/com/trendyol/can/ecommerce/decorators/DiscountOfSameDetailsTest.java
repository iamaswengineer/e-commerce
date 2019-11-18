package tr.com.trendyol.can.ecommerce.decorators;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tr.com.trendyol.can.ecommerce.services.dto.DiscountDecoratorDTO;
import tr.com.trendyol.can.ecommerce.util.TestUtil;

import static org.junit.jupiter.api.Assertions.*;

class DiscountOfSameDetailsTest {

    private DiscountOfSameDetails discountOfSameDetails;

    @BeforeEach
    private void init() {
        DiscountRateOf50 discountRateOf50 = new DiscountRateOf50();
        DiscountRateOf20 discountRateOf20 = new DiscountRateOf20(discountRateOf50);
        discountOfSameDetails = new DiscountOfSameDetails(discountRateOf20);
    }

    @Test
    void apply() {
        DiscountDecoratorDTO discountDecoratorDTO = TestUtil.initializeDiscountDecoratorDTO(3L);
        DiscountDecoratorDTO _discountDecoratorDTO = discountOfSameDetails.apply(discountDecoratorDTO);
        assertNotNull(_discountDecoratorDTO);
        assertEquals(5.0, _discountDecoratorDTO.getCampaignDiscountAmount());
        DiscountDecoratorDTO discountDecoratorDTO1 = TestUtil.initializeDiscountDecoratorDTO(1L);
        assertNotNull(discountDecoratorDTO1);
        assertEquals(0.0, discountDecoratorDTO1.getCampaignDiscountAmount());

    }

}