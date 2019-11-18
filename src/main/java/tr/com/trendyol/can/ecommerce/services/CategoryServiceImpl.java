package tr.com.trendyol.can.ecommerce.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tr.com.trendyol.can.ecommerce.controllers.dtos.CategoryDTO;
import tr.com.trendyol.can.ecommerce.entities.Category;
import tr.com.trendyol.can.ecommerce.exceptions.CategoryAlreadyDefinedException;
import tr.com.trendyol.can.ecommerce.exceptions.CategoryNotFoundException;
import tr.com.trendyol.can.ecommerce.repositories.CategoryRepository;

@Service
public class CategoryServiceImpl implements CategoryService{

    private CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Category saveOne(CategoryDTO categoryDTO) {
        Category category = findByTitle(categoryDTO.getTitle());
        if(category != null){
            throw new CategoryAlreadyDefinedException("Category is already defined:" + category.getId());
        }
        Category _category = new Category();
        _category.setTitle(categoryDTO.getTitle());
        return categoryRepository.save(_category);
    }

    @Override
    public Category findBy(Long id) {
        if(!categoryRepository.existsById(id)){
            throw new CategoryNotFoundException("User did not found with id: " + id);
        }
        return categoryRepository.findBy(id);
    }

    @Override
    public Category findByTitle(String title) {
        return categoryRepository.findByTitle(title);
    }
}
