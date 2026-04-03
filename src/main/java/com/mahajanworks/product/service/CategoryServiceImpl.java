package com.mahajanworks.product.service;

import com.mahajanworks.product.dto.CategoryDTO;
import com.mahajanworks.product.exception.CategoryAlreadyExistsException;
import com.mahajanworks.product.exception.CategoryNotFoundException;
import com.mahajanworks.product.mapper.CategoryMapper;
import com.mahajanworks.product.model.Category;
import com.mahajanworks.product.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final Logger log = LoggerFactory.getLogger(CategoryServiceImpl.class);

    private final CategoryRepository categoryRepository;

    public CategoryDTO createCategory(CategoryDTO categoryDTO) {
        log.info("Checking Category: {} exists in db", categoryDTO.getName());
        Optional<Category> optionalCategory = categoryRepository.findByName(categoryDTO.getName());

        if (optionalCategory.isEmpty()) {
            log.info("Category: {} not exists in db", categoryDTO.getName());
            Category category = CategoryMapper.toCategory(categoryDTO);
            log.info("saving Category: {} in db", category.getName());
            category = categoryRepository.save(category);
            log.info("Category: {} saved and returned", category.getName());
            return CategoryMapper.toCategoryDTO(category);
        }else{
            log.info("Category: {} already exists in db, and throws related exception", categoryDTO.getName());
            throw new CategoryAlreadyExistsException("Category " + categoryDTO.getName() + " already exists in db");
        }
    }

    @Override
    public List<CategoryDTO> getCategories() {
        log.info("fetching all categories from db");
        List<Category> categories = categoryRepository.findAll();
        log.info("fetched all categories from db, converted to dto and return");
        return categories.stream().map(CategoryMapper::toCategoryDTO).toList();
    }

    @Override
    public CategoryDTO findById(Long id) {
        log.info("fetching category by id: {} from db", id);

        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> {
                    log.info("Category id: {} not found in db, so exception thrown", id);
                    return new CategoryNotFoundException("Category Id: " + id + " not found in db");
                }
        );

        log.info("fetched category by id: {} from db and return it", id);
        return CategoryMapper.toCategoryDTO(category);

    }

    @Override
    public CategoryDTO updateCategory(Long id, CategoryDTO categoryDTO) {
        log.info("fetching category by id: {} from db", id);

        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> {
                            log.info("Category id: {} not found in db, so exception thrown", id);
                            return new CategoryNotFoundException("Category Id: " + id + " not found in db");
                        }
                );

        log.info("Category id: {} found in db and new details updated", id);
        category.setName(categoryDTO.getName());

        log.info("saving updated Category: {} into db", category);
        category = categoryRepository.save(category);

        log.info("returning updated new category: {}", category);
        return CategoryMapper.toCategoryDTO(category);
    }

    @Override
    public Map<String, String> deleteCategory(Long id) {
        log.info("deleting category by id: {} from db", id);

        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> {
                            log.info("Category id: {} not found in db, so exception thrown", id);
                            return new CategoryNotFoundException("Category Id: " + id + " not found in db");
                        }
                );

        log.info("Category: {} deleted from db",category);
        categoryRepository.delete(category);

        log.info("returning message after deletion");
        return Map.of("message", "Category: "+ category + " deleted successfully");
    }
}
