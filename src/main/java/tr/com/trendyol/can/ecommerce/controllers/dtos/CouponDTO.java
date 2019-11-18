package tr.com.trendyol.can.ecommerce.controllers.dtos;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Getter
@Setter
@ApiModel(description = "Data transfer object for Coupon Model")
public class CouponDTO {

    @Min(value = 0, message = "Amount should not be negative")
    @ApiModelProperty(notes = "Amount should not be negative")
    private Double amount;

    @Min(value = 1, message = "Value of discount strategy should not be minimum 1 and maximum 2")
    @Max(value = 2, message = "Value of discount strategy should not be minimum 1 and maximum 2")
    @ApiModelProperty(notes = "Value of discount strategy should not be minimum 1 and maximum 2")
    private Long discountStrategy;
}
