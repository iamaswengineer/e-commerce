package tr.com.trendyol.can.ecommerce.controllers;

import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tr.com.trendyol.can.ecommerce.controllers.dtos.CouponDTO;
import tr.com.trendyol.can.ecommerce.entities.Coupon;
import tr.com.trendyol.can.ecommerce.entities.enums.DiscountStrategy;
import tr.com.trendyol.can.ecommerce.services.CouponService;

@RestController
@RequestMapping("coupon")
@NoArgsConstructor
public class CouponRestController {

    private CouponService couponService;

    @Autowired
    public CouponRestController(CouponService couponService) {
        this.couponService = couponService;
    }

    @PostMapping("saveOne")
    public ResponseEntity saveOne(@RequestBody CouponDTO couponDTO){
        Coupon saved = couponService.saveOne(couponDTO);
        return new ResponseEntity<>(saved.getId(), HttpStatus.CREATED);
    }
}
