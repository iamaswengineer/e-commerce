package tr.com.trendyol.can.ecommerce.controllers;

import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tr.com.trendyol.can.ecommerce.controllers.dtos.ProductDTO;
import tr.com.trendyol.can.ecommerce.entities.Category;
import tr.com.trendyol.can.ecommerce.entities.Product;
import tr.com.trendyol.can.ecommerce.services.CategoryService;
import tr.com.trendyol.can.ecommerce.services.ProductService;

@RestController
@RequestMapping("product")
@NoArgsConstructor
public class ProductRestController {

    private ProductService productService;

    @Autowired
    public ProductRestController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping(value="/saveOne")
    public ResponseEntity saveOne(@RequestBody ProductDTO productDTO){
        Product saved = productService.saveOne(productDTO);
        return new ResponseEntity<>(saved.getId(), HttpStatus.CREATED);
    }
}
