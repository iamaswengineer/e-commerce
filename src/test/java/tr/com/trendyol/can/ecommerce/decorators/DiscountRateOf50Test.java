package tr.com.trendyol.can.ecommerce.decorators;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import tr.com.trendyol.can.ecommerce.services.dto.DiscountDecoratorDTO;
import tr.com.trendyol.can.ecommerce.util.TestUtil;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class DiscountRateOf50Test {

    private DiscountRateOf50 discountRateOf50;

    @BeforeEach
    private void init() {
        discountRateOf50 = new DiscountRateOf50();
    }

    @Test
    void apply() {
        DiscountDecoratorDTO discountDecoratorDTO = TestUtil.initializeDiscountDecoratorDTO(6L);
        DiscountDecoratorDTO _discountDecoratorDTO = discountRateOf50.apply(discountDecoratorDTO);
        assertNotNull(_discountDecoratorDTO);
        assertEquals(600.0, _discountDecoratorDTO.getCampaignDiscountAmount());
        DiscountDecoratorDTO discountDecoratorDTO1 = TestUtil.initializeDiscountDecoratorDTO(1L);
        assertNotNull(discountDecoratorDTO1);
        assertEquals(0.0, discountDecoratorDTO1.getCampaignDiscountAmount());
    }

}