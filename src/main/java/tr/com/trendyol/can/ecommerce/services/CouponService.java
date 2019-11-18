package tr.com.trendyol.can.ecommerce.services;

import tr.com.trendyol.can.ecommerce.controllers.dtos.CouponDTO;
import tr.com.trendyol.can.ecommerce.entities.Coupon;

public interface CouponService {
    Coupon saveOne(CouponDTO couponDTO);
}
