package tr.com.trendyol.can.ecommerce.controllers;

import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tr.com.trendyol.can.ecommerce.controllers.dtos.ShoppingCartDTO;
import tr.com.trendyol.can.ecommerce.entities.Product;
import tr.com.trendyol.can.ecommerce.services.ShoppingCartService;

@RestController
@RequestMapping("shoppingCart")
@NoArgsConstructor
public class ShoppingCartRestController {

    private ShoppingCartService shoppingCartService;

    @Autowired
    public ShoppingCartRestController(ShoppingCartService shoppingCartService) {
        this.shoppingCartService = shoppingCartService;
    }

    @PostMapping(value="/saveOne")
    public ResponseEntity saveOne(@RequestBody ShoppingCartDTO shoppingCartDTO){
        Product product = shoppingCartService.saveOne(shoppingCartDTO);
        return new ResponseEntity<>(product.getId(), HttpStatus.OK);
    }

    @GetMapping("{id}/totalAmountAfterDiscount")
    public ResponseEntity getTotalCartAmount(@PathVariable Long id){
        return new ResponseEntity<>(shoppingCartService.fetchCartPriceAfterDiscount(id), HttpStatus.OK);
    }

    @GetMapping("{id}/couponDiscountAmount")
    public ResponseEntity getCouponDiscountAmount(@PathVariable Long id){
        return new ResponseEntity<>(shoppingCartService.fetchCouponDiscount(id), HttpStatus.OK);
    }

    @GetMapping("{id}/campaignDiscountAmount")
    public ResponseEntity getCampaignDiscountAmount(@PathVariable Long id){
        return new ResponseEntity<>(shoppingCartService.fetchCampaignDiscount(id), HttpStatus.OK);
    }

    @GetMapping("{id}/deliveryCost")
    public ResponseEntity getDeliveryCost(@PathVariable Long id){
        return new ResponseEntity<>(shoppingCartService.getDeliveryCost(id), HttpStatus.OK);
    }
}
