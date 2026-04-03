package com.mahajanworks.product.service;

import com.mahajanworks.product.dto.ProductDTO;
import com.mahajanworks.product.exception.CategoryNotFoundException;
import com.mahajanworks.product.model.Category;
import com.mahajanworks.product.model.Product;
import com.mahajanworks.product.repository.CategoryRepository;
import com.mahajanworks.product.repository.ProductRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {

    @Mock
    private ProductRepository productRepository;

    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private ProductServiceImpl productService;


    ProductDTO productDTO;
    Product product;
    Category category;


    @BeforeEach
    void setUp(){
        category = new Category();
        category.setId(1L);
        category.setName("Test Category");


       productDTO = new ProductDTO();
//       productDTO.setId(1L);
       productDTO.setName("Test");
       productDTO.setDescription("Test Description");
       productDTO.setPrice(9.99);
       productDTO.setCategoryId(category.getId());

        product = new Product();
        product.setName("Test");
        product.setDescription("Test Description");
        product.setPrice(9.99);
        product.setCategory(category);

    }

    @AfterEach
    void tearDown(){

    }

    @Test
    void addProduct_whenSuccess_test() {

        when(categoryRepository.findById(productDTO.getCategoryId())).thenReturn(Optional.ofNullable(category));
        when(productRepository.save(product)).thenReturn(product);

        ProductDTO savedProductDTO = productService.addProduct(productDTO);
        System.out.println("Saved ProductDTO id: "+savedProductDTO.getId());
        assertNotNull(savedProductDTO);
        assertEquals(product.getName(), savedProductDTO.getName());
        verify(categoryRepository, times(1)).findById(productDTO.getCategoryId());
        verify(productRepository, times(1)).save(product);
    }


    @Test
    void addProduct_whenFail_test(){
        when(categoryRepository.findById(productDTO.getCategoryId())).thenThrow(CategoryNotFoundException.class);
        assertThrows(CategoryNotFoundException.class, () -> productService.addProduct(productDTO));
    }

    @Test
    void getProducts_whenSuccess_test() {
        when(productRepository.findAll()).thenReturn(List.of(product));
        List<ProductDTO> productDTOS = productService.getProducts();
        assertNotNull(productDTOS);
        assertEquals(productDTO.getName(), productDTOS.getFirst().getName());
    }
}