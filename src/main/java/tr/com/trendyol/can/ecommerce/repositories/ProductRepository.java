package tr.com.trendyol.can.ecommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import tr.com.trendyol.can.ecommerce.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("select p from Product p where p.id = :id")
    Product findBy(Long id);
}
