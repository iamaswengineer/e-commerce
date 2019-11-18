package tr.com.trendyol.can.ecommerce.services.cartcache;

import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import tr.com.trendyol.can.ecommerce.services.dto.DiscountDecoratorDTO;
import tr.com.trendyol.can.ecommerce.services.dto.ShoppingCartDetailServiceDTO;

import java.util.*;

@Component
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class ShoppingCartCache {

    private Map<Long, List<ShoppingCartDetailServiceDTO>> cache = new HashMap<>();

    @Getter
    private Map<Long, Set<ShoppingCartDetailServiceDTO>> detailMapByCategory = new HashMap<>();

    @Getter
    private Map<ShoppingCartDetailServiceDTO, Long> quantityMapByDetail = new HashMap<>();

    public List<ShoppingCartDetailServiceDTO> findByUserId(Long id) {
        if (cache.get(id) != null) {
            return cache.get(id);
        }
        return new ArrayList<>();
    }

    public void update(Long id, List<ShoppingCartDetailServiceDTO> dtoList) {
        cache.put(id, dtoList);
    }

    public void constructByShoppingCartDetailQuantityMap(Long userId) {

        for (ShoppingCartDetailServiceDTO shoppingCartDetail : this.cache.get(userId)) {
            if (this.quantityMapByDetail.size() == 0) {
                this.quantityMapByDetail.put(shoppingCartDetail, shoppingCartDetail.getQuantity());
                return;
            }
            for (Map.Entry<ShoppingCartDetailServiceDTO, Long> entry : quantityMapByDetail.entrySet()) {
                if (entry.getKey().getCategoryId().equals(shoppingCartDetail.getCategoryId()) && entry.getKey().getProductId().equals(shoppingCartDetail.getProductId())) {
                    entry.getKey().setQuantity(shoppingCartDetail.getQuantity());
                    entry.getKey().setTotalPrice(shoppingCartDetail.getTotalPrice());
                    entry.setValue(shoppingCartDetail.getQuantity());
                } else {
                    this.quantityMapByDetail.put(shoppingCartDetail, shoppingCartDetail.getQuantity());
                }
            }
        }
    }

    public void constructCategoryShoppingCartDetailMap(Long userId) {

        for (ShoppingCartDetailServiceDTO shoppingCartDetail : this.cache.get(userId)) {
            Long categoryId = shoppingCartDetail.getCategoryId();
            if (!this.detailMapByCategory.containsKey(categoryId)) {
                Set<ShoppingCartDetailServiceDTO> shoppingCardDetailList = new HashSet<>();
                shoppingCardDetailList.add(shoppingCartDetail);
                detailMapByCategory.put(categoryId, shoppingCardDetailList);
            }else{
                detailMapByCategory.get(userId).add(shoppingCartDetail);
            }
        }
    }
}
