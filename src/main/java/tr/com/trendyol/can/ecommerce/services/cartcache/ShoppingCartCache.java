package tr.com.trendyol.can.ecommerce.services.cartcache;

import lombok.Getter;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import tr.com.trendyol.can.ecommerce.entities.User;
import tr.com.trendyol.can.ecommerce.services.dto.ShoppingCartDetailServiceDTO;

import java.util.*;

@Component
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class ShoppingCartCache {

    @Getter
    private Map<Long, Stack<ShoppingCartDetailServiceDTO>> cache = new HashMap<>();

    @Getter
    private Map<Long, Double> totalCartPriceMapByUser = new HashMap<>();

    @Getter
    private Map<Long, List<ShoppingCartDetailServiceDTO>> detailMapByCategory = new HashMap<>();

    @Getter
    private Map<ShoppingCartDetailServiceDTO, Long> quantityMapByDetail = new HashMap<>();

    public Stack<ShoppingCartDetailServiceDTO> findByUser(User user) {
        if (cache.get(user.getId()) != null) {
            return cache.get(user.getId());
        }
        return new Stack<>();
    }

    public void update(User user, ShoppingCartDetailServiceDTO detail) {
        if (cache.get(user.getId()) == null) {
            Stack<ShoppingCartDetailServiceDTO> detailStack = new Stack<>();
            detailStack.push(detail);
            cache.put(user.getId(), detailStack);
        } else {
            cache.get(user.getId()).push(detail);
        }
    }

    public void constructCategoryShoppingCartDetailMap(User user) {

        ShoppingCartDetailServiceDTO shoppingCartDetail = this.cache.get(user.getId()).peek();
        List<ShoppingCartDetailServiceDTO> detailList;

        if (this.detailMapByCategory.size() == 0) {
            detailList = new ArrayList<>();
            detailList.add(shoppingCartDetail);
            this.detailMapByCategory.put(shoppingCartDetail.getCategoryId(), detailList);
            return;
        }

        if (!this.detailMapByCategory.containsKey(shoppingCartDetail.getCategoryId())) {
            detailList = new ArrayList<>();
            detailList.add(shoppingCartDetail);
            detailMapByCategory.put(shoppingCartDetail.getCategoryId(), detailList);
        } else {
            List<ShoppingCartDetailServiceDTO> itemList = detailMapByCategory.get(shoppingCartDetail.getCategoryId());
            if (!itemList.contains(shoppingCartDetail)) {
                itemList.add(shoppingCartDetail);
            }
        }
    }


    public void updateTotalCartPriceOfUser(User user, Double totalItemPrice) {
        if (totalCartPriceMapByUser.size() == 0) {
            totalCartPriceMapByUser.put(user.getId(), totalItemPrice);
        } else if (totalCartPriceMapByUser.get(user.getId()) != null) {
            Double price = totalCartPriceMapByUser.get(user.getId());
            price = price + totalItemPrice;
            totalCartPriceMapByUser.put(user.getId(), price);
        } else {
            totalCartPriceMapByUser.put(user.getId(), totalItemPrice);
        }
    }
}
