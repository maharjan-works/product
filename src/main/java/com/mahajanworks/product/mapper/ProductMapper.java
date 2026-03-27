package com.mahajanworks.product.mapper;

import com.mahajanworks.product.dto.ProductDTO;
import com.mahajanworks.product.model.Category;
import com.mahajanworks.product.model.Product;

public class ProductMapper {

    public static Product toProduct(ProductDTO productDTO, Category category){
        return Product.builder()
                .name(productDTO.getName())
                .description(productDTO.getDescription())
                .price(productDTO.getPrice())
                .category(category)
                .build();
    }

    public static ProductDTO toProductDTO(Product product){
        return ProductDTO.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .categoryId(product.getCategory().getId())
                .build();
    }
}
