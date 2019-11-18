package tr.com.trendyol.can.ecommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tr.com.trendyol.can.ecommerce.entities.Discount;

public interface DiscountRepository<T extends Discount> extends JpaRepository<T, Long> {
}
