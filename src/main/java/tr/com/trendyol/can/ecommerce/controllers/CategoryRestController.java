package tr.com.trendyol.can.ecommerce.controllers;

import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tr.com.trendyol.can.ecommerce.controllers.dtos.CategoryDTO;
import tr.com.trendyol.can.ecommerce.entities.Category;
import tr.com.trendyol.can.ecommerce.services.CategoryService;

@RestController
@RequestMapping("category")
@NoArgsConstructor
public class CategoryRestController {

    private CategoryService categoryService;

    @Autowired
    public CategoryRestController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping(value="/saveOne")
    public ResponseEntity saveOne(@RequestBody CategoryDTO categoryDTO){
        Category category1 = categoryService.saveOne(categoryDTO);
        return new ResponseEntity<>(category1.getId(), HttpStatus.CREATED);
    }
}
