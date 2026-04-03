package com.mahajanworks.product.service;

import com.mahajanworks.product.dto.CategoryDTO;
import com.mahajanworks.product.exception.CategoryAlreadyExistsException;
import com.mahajanworks.product.exception.CategoryNotFoundException;
import com.mahajanworks.product.model.Category;
import com.mahajanworks.product.repository.CategoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CategoryServiceImplTest {

    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private CategoryServiceImpl categoryService;

    CategoryDTO categoryDTO;
    Category category;


    @BeforeEach
    public void setUp(){
        categoryDTO = new CategoryDTO();
        categoryDTO.setId(1L);
        categoryDTO.setName("Test");

        category = new Category();
        category.setId(1L);
        category.setName("Test");
    }



    @Test
    void createCategory_whenSuccess_test() {
        when(categoryRepository.findByName("Test")).thenReturn(Optional.empty());
        when(categoryRepository.save(any(Category.class))).thenReturn(category);

        CategoryDTO savedCategory = categoryService.createCategory(categoryDTO);

        assertNotNull(savedCategory);
        assertEquals("Test",savedCategory.getName());
        verify(categoryRepository, times(1)).save(any(Category.class));
    }

    @Test
    void createCategory_whenFail_test(){
        when(categoryRepository.findByName(categoryDTO.getName())).thenReturn(Optional.of(category));
        assertThrows(CategoryAlreadyExistsException.class, () -> categoryService.createCategory(categoryDTO));
        verify(categoryRepository, times(1)).findByName(categoryDTO.getName());

    }

    @Test
    void getCategories() {
        when(categoryRepository.findAll()).thenReturn(List.of(category));

        List<CategoryDTO> categoryDTOS = categoryService.getCategories();

        assertNotNull(categoryDTOS);
        assertEquals(categoryDTO.getName(),categoryDTOS.get(0).getName());
        verify(categoryRepository, times(1)).findAll();
    }

    @Test
    void findById_whenSuccess_test() {
        when(categoryRepository.findById(categoryDTO.getId())).thenReturn(Optional.of(category));

        CategoryDTO foundCategory = categoryService.findById(categoryDTO.getId());
        assertNotNull(foundCategory);
        assertEquals(category.getName(), foundCategory.getName());
        verify(categoryRepository, times(1)).findById(categoryDTO.getId());
    }

    @Test
    void findById_whenFail_test(){
        when(categoryRepository.findById(categoryDTO.getId())).thenReturn(Optional.empty());

        assertThrows(CategoryNotFoundException.class, () -> categoryService.findById(categoryDTO.getId()));
        verify(categoryRepository, times(1)).findById(categoryDTO.getId());
    }

    @Test
    void updateCategory_whenSuccess_test() {
        when(categoryRepository.findById(categoryDTO.getId())).thenReturn(Optional.of(category));
        when(categoryRepository.save(category)).thenReturn(category);

        CategoryDTO updatedCategory = categoryService.updateCategory(categoryDTO.getId(), categoryDTO);
        assertNotNull(updatedCategory);
        assertEquals(categoryDTO.getName(), updatedCategory.getName());
        verify(categoryRepository, times(1)).findById(categoryDTO.getId());
    }

    @Test
    void updateCategory_whenFail_test(){
        when(categoryRepository.findById(categoryDTO.getId())).thenThrow(CategoryNotFoundException.class);
        assertThrows(CategoryNotFoundException.class, () -> categoryService.updateCategory(categoryDTO.getId(), categoryDTO));
        verify(categoryRepository, times(1)).findById(categoryDTO.getId());
    }

    @Test
    void deleteCategory_whenSuccess_test() {
       when(categoryRepository.findById(categoryDTO.getId())).thenReturn(Optional.of(category));

       Map<String,String> testMap = Map.of("message", "Category: Category(id=1, name=Test, products=null) deleted successfully");

       Map<String,String> returnMap = categoryService.deleteCategory(categoryDTO.getId());
       assertNotNull(returnMap);
       assertEquals(testMap.get("message"), returnMap.get("message"));
    }


    @Test
    void deleteCategory_whenFail_test() {
        when(categoryRepository.findById(categoryDTO.getId())).thenThrow(CategoryNotFoundException.class);

        assertThrows(CategoryNotFoundException.class, () -> categoryService.deleteCategory(categoryDTO.getId()));
        verify(categoryRepository, times(1)).findById(categoryDTO.getId());
    }
}