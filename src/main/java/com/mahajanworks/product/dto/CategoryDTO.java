package com.mahajanworks.product.dto;

import com.mahajanworks.product.model.Category;
import com.mahajanworks.product.model.Product;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Schema(
        name = "Category",
        description = "contains category info as well as products in it"
)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoryDTO {
    private Long id;
    private String name;
    private List<ProductDTO> products;
}
