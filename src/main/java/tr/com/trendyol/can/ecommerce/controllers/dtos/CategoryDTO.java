package tr.com.trendyol.can.ecommerce.controllers.dtos;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Size;

@Getter
@Setter
@ApiModel(description = "Data transfer object for Category Model")
public class CategoryDTO {

    @Size(max = 255, message = "Property value can be maximum 255 characters")
    @Size(min = 3, message = "Property value can be minimum 3 characters")
    @ApiModelProperty(notes = "Property value can be maximum 255 characters and minimum 3 characters")
    private String title;
}
