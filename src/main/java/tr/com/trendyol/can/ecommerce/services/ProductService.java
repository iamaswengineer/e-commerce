package tr.com.trendyol.can.ecommerce.services;

import org.springframework.stereotype.Service;
import tr.com.trendyol.can.ecommerce.controllers.dtos.ProductDTO;
import tr.com.trendyol.can.ecommerce.entities.Product;

@Service
public interface ProductService {

    Product saveOne(ProductDTO productDTO);
    Product findBy(Long id);
}
