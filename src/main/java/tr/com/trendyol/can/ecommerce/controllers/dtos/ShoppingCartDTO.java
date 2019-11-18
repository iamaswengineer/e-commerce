package tr.com.trendyol.can.ecommerce.controllers.dtos;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "Data transfer object for ShoppingCart Model")
public class ShoppingCartDTO {

    private Long userId;
    private Long productId;
    private Long quantity;
}
