package tr.com.trendyol.can.ecommerce.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import tr.com.trendyol.can.ecommerce.services.dto.DiscountDecoratorDTO;
import tr.com.trendyol.can.ecommerce.services.dto.ShoppingCartDetailServiceDTO;

import java.io.IOException;
import java.util.*;

public class TestUtil {

    public static DiscountDecoratorDTO initializeDiscountDecoratorDTO(Long size){
        DiscountDecoratorDTO myDiscountDecoratorDTO = new DiscountDecoratorDTO();
        myDiscountDecoratorDTO.setCartPriceAfterDiscount(0.0);
        myDiscountDecoratorDTO.setCouponDiscountAmount(0.0);
        myDiscountDecoratorDTO.setTotalCartPrice(0.0);
        myDiscountDecoratorDTO.setCampaignDiscountAmount(0.0);
        ShoppingCartDetailServiceDTO shoppingCartDetailServiceDTO;
        List<ShoppingCartDetailServiceDTO> detailServiceDTOSet = new ArrayList<>();
        Map<Long, List<ShoppingCartDetailServiceDTO>> entry = new HashMap<>();
        Map<ShoppingCartDetailServiceDTO, Long> quantityMapByDetail = new HashMap<>();
        for (int i = 0; i < size; i++) {
            shoppingCartDetailServiceDTO = new ShoppingCartDetailServiceDTO(1L, 200.0, 1L, 200.0, 1L);
            detailServiceDTOSet.add(shoppingCartDetailServiceDTO);
            myDiscountDecoratorDTO.setTotalCartPrice(myDiscountDecoratorDTO.getTotalCartPrice() + shoppingCartDetailServiceDTO.getPrice() * shoppingCartDetailServiceDTO.getQuantity());
            entry.put(shoppingCartDetailServiceDTO.getCategoryId(), detailServiceDTOSet);
            myDiscountDecoratorDTO.setDetailMapByCategory(entry);
        }
        ShoppingCartDetailServiceDTO _shoppingCartDetailServiceDTO = new ShoppingCartDetailServiceDTO(size, 200.0, 1L, 200.0, 1L);
        myDiscountDecoratorDTO.setTotalCartPrice(_shoppingCartDetailServiceDTO.getPrice() * _shoppingCartDetailServiceDTO.getQuantity());
        quantityMapByDetail.put(_shoppingCartDetailServiceDTO, size);
       // myDiscountDecoratorDTO.setQuantityMapByDetail(quantityMapByDetail);
        return myDiscountDecoratorDTO;
    }
}
