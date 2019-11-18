package tr.com.trendyol.can.ecommerce.services.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DiscountServiceDTO {
    private Long categoryId;
    private Double amount;
    private Long quantity;
    private Long discountStrategy;

    public DiscountServiceDTO(Long categoryId, Double amount, Long quantity, Long discountStrategy) {
        this.categoryId = categoryId;
        this.amount = amount;
        this.quantity = quantity;
        this.discountStrategy = discountStrategy;
    }

    public DiscountServiceDTO(Long categoryId, Double amount, Long discountStrategy) {
        this.categoryId = categoryId;
        this.amount = amount;
        this.discountStrategy = discountStrategy;
    }
}
