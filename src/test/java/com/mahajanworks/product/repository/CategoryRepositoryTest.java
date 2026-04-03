package com.mahajanworks.product.repository;

import com.mahajanworks.product.model.Category;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ActiveProfiles("test")
@DataJpaTest
class CategoryRepositoryTest {

    private CategoryRepository categoryRepository;


    Category category;

    @BeforeEach
    void setUp() {
        category = new Category();
        category.setName("Test");
        categoryRepository.save(category);
    }

    @AfterEach
    void tearDown() {

    }

    @Test
    void findByName() {
        Category dbCategory = categoryRepository.findByName("Test").orElse(null);
        assertNotNull(dbCategory);
        assertEquals(category.getName(), dbCategory.getName());
    }





}