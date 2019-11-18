package tr.com.trendyol.can.ecommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import tr.com.trendyol.can.ecommerce.entities.ShoppingCartDetail;

public interface ShoppingCardDetailRepository extends JpaRepository<ShoppingCartDetail, Long> {

}
