package tr.com.trendyol.can.ecommerce.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tr.com.trendyol.can.ecommerce.controllers.dtos.ProductDTO;
import tr.com.trendyol.can.ecommerce.entities.Category;
import tr.com.trendyol.can.ecommerce.entities.Product;
import tr.com.trendyol.can.ecommerce.repositories.CategoryRepository;
import tr.com.trendyol.can.ecommerce.repositories.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService{

    private ProductRepository productRepository;
    private CategoryRepository categoryRepository;
    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Product saveOne(ProductDTO productDTO) {
        Product product = new Product();
        Category one = categoryRepository.findBy(productDTO.getCategory());
        product.setCategory(one);
        product.setPrice(productDTO.getPrice());
        product.setTitle(productDTO.getTitle());
        return productRepository.save(product);
    }

    @Override
    public Product findBy(Long id) {
        return productRepository.findBy(id);
    }
}
