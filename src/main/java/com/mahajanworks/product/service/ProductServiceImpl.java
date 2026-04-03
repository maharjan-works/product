package com.mahajanworks.product.service;

import com.mahajanworks.product.dto.ProductDTO;
import com.mahajanworks.product.exception.CategoryNotFoundException;
import com.mahajanworks.product.mapper.ProductMapper;
import com.mahajanworks.product.model.Category;
import com.mahajanworks.product.model.Product;
import com.mahajanworks.product.repository.CategoryRepository;
import com.mahajanworks.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService{
    private final Logger log = LoggerFactory.getLogger(ProductServiceImpl.class);
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;


    @Override
    public ProductDTO addProduct(ProductDTO productDTO) {
        log.info("saving product : {} into db", productDTO);

        log.info("checking category id: {} exists in db", productDTO.getCategoryId());
        Category category = categoryRepository.findById(productDTO.getCategoryId())
                .orElseThrow(() -> {
                    log.info("categoryId: {} not found in db",productDTO.getCategoryId());
                    return new CategoryNotFoundException("CategoryId: "+productDTO.getCategoryId() + "not found in db");
                }) ;

        Product product = ProductMapper.toProduct(productDTO,category);

        product = productRepository.save(product);

        System.out.println("saved product: "+product);
        log.info("product saved into db and returned category");
        return ProductMapper.toProductDTO(product);
    }

    @Override
    public List<ProductDTO> getProducts() {
        List<Product> products = productRepository.findAll();
        return products.stream().map(ProductMapper::toProductDTO).toList();
    }



}
