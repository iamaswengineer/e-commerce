package tr.com.trendyol.can.ecommerce.services.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ShoppingCartDetailServiceDTO {

    private Long quantity;

    private Double totalPrice;

    private Long productId;

    private Double price;

    private Long categoryId;

    public void incrementQuantity(Long quantity){
        this.quantity += quantity;
    }
}
