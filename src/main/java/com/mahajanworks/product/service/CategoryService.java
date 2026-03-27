package com.mahajanworks.product.service;

import com.mahajanworks.product.dto.CategoryDTO;

import java.util.List;
import java.util.Map;

public interface CategoryService {

    CategoryDTO createCategory(CategoryDTO categoryDTO);

    List<CategoryDTO> getCategories();

    CategoryDTO findById(Long id);

    CategoryDTO updateCategory(Long id, CategoryDTO categoryDTO);

    Map<String, String> deleteCategory(Long id);
}
