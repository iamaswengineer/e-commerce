package tr.com.trendyol.can.ecommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tr.com.trendyol.can.ecommerce.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {

    User findUserById(Long id);
}
