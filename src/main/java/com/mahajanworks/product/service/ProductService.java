package com.mahajanworks.product.service;

import com.mahajanworks.product.dto.ProductDTO;

import java.util.List;

public interface ProductService {

    ProductDTO addProduct(ProductDTO  productdto);
    List<ProductDTO>  getProducts();
}

