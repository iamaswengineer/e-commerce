package tr.com.trendyol.can.ecommerce.services;

import tr.com.trendyol.can.ecommerce.controllers.dtos.CategoryDTO;
import tr.com.trendyol.can.ecommerce.entities.Category;

public interface CategoryService {

    Category saveOne(CategoryDTO categoryDTO);
    Category findBy(Long id);
    Category findByTitle(String title);
}
