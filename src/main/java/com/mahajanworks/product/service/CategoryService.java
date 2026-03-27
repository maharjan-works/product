package com.mahajanworks.product.service;

import com.mahajanworks.product.dto.CategoryDTO;

import java.util.List;

public interface CategoryService {

    CategoryDTO createCategory(CategoryDTO categoryDTO);

    List<CategoryDTO> getCategories();
}
