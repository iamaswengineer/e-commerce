package tr.com.trendyol.can.ecommerce.services;

import tr.com.trendyol.can.ecommerce.entities.User;

public interface UserService {
    User findById(Long id);
}
