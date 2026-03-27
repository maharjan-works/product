package com.mahajanworks.product.mapper;

import com.mahajanworks.product.dto.CategoryDTO;
import com.mahajanworks.product.model.Category;

import java.util.ArrayList;

public class CategoryMapper {

    public static Category toCategory(CategoryDTO categoryDTO) {
        return Category.builder()
                .name(categoryDTO.getName())
                .build();
    }

    public static CategoryDTO toCategoryDTO(Category category) {
        return CategoryDTO.builder()
                .id(category.getId())
                .name(category.getName())
                .products((category.getProducts() == null) ?
                        new ArrayList<>() :
                        category.getProducts().stream().map(ProductMapper::toProductDTO).toList())
                .build();
    }
}
