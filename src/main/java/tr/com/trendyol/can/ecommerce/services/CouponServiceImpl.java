package tr.com.trendyol.can.ecommerce.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tr.com.trendyol.can.ecommerce.controllers.dtos.CouponDTO;
import tr.com.trendyol.can.ecommerce.entities.Coupon;
import tr.com.trendyol.can.ecommerce.entities.enums.DiscountStrategy;
import tr.com.trendyol.can.ecommerce.repositories.CouponRepository;

@Service
public class CouponServiceImpl implements CouponService{

    private CouponRepository couponRepository;

    @Autowired
    public CouponServiceImpl(CouponRepository couponRepository) {
        this.couponRepository = couponRepository;
    }

    @Override
    public Coupon saveOne(CouponDTO couponDTO) {
        Coupon coupon = new Coupon();
        coupon.setAmount(couponDTO.getAmount());
        coupon.setDiscountStrategy(couponDTO.getDiscountStrategy() == 1 ? DiscountStrategy.AMOUNT.getValue() : DiscountStrategy.RATE.getValue());
        return couponRepository.save(coupon);
    }
}
