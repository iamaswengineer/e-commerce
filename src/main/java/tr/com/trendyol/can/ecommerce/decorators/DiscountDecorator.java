package tr.com.trendyol.can.ecommerce.decorators;

import tr.com.trendyol.can.ecommerce.services.dto.DiscountDecoratorDTO;

interface DiscountDecorator {

    DiscountDecoratorDTO apply(DiscountDecoratorDTO decoratable);
}
