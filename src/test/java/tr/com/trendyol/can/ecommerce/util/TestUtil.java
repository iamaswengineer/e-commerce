package tr.com.trendyol.can.ecommerce.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import tr.com.trendyol.can.ecommerce.services.dto.DiscountDecoratorDTO;
import tr.com.trendyol.can.ecommerce.services.dto.ShoppingCartDetailServiceDTO;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class TestUtil {

    public static byte[] convertObjectToJsonBytes(Object object) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        return mapper.writeValueAsBytes(object);
    }

    public static DiscountDecoratorDTO initializeDiscountDecoratorDTO(Long size){
        DiscountDecoratorDTO myDiscountDecoratorDTO = new DiscountDecoratorDTO();
        myDiscountDecoratorDTO.setCartPriceAfterDiscount(0.0);
        myDiscountDecoratorDTO.setCouponDiscountAmount(0.0);
        myDiscountDecoratorDTO.setTotalCartPrice(0.0);
        myDiscountDecoratorDTO.setCampaignDiscountAmount(0.0);
        ShoppingCartDetailServiceDTO shoppingCartDetailServiceDTO;
        Set<ShoppingCartDetailServiceDTO> detailServiceDTOSet = new HashSet<>();
        Map<Long, Set<ShoppingCartDetailServiceDTO>> entry = new HashMap<>();
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
        myDiscountDecoratorDTO.setQuantityMapByDetail(quantityMapByDetail);
        return myDiscountDecoratorDTO;
    }
}
