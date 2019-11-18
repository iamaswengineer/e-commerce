package tr.com.trendyol.can.ecommerce.controllers.dtos;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

@Getter
@Setter
@ApiModel(description = "Data transfer object for Campaign Model")
public class ProductDTO {

    @Size(max = 255, message = "Property value can be maximum 255 characters")
    @Size(min = 3, message = "Property value can be minimum 3 characters")
    @ApiModelProperty(notes = "Property value can be maximum 255 characters and minimum 3 characters")
    private String title;

    @Min(value = 0, message = "Amount should not be negative")
    @ApiModelProperty(notes = "Amount should not be negative")
    private Double price;

    @Min(value = 1, message = "Property value should be minimum 1")
    @ApiModelProperty(notes = "Property value should be minimum 1")
    private Long category;

}
