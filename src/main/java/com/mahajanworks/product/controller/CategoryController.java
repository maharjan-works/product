package com.mahajanworks.product.controller;

import com.mahajanworks.product.dto.CategoryDTO;
import com.mahajanworks.product.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

@Tag(
        name="Category Endpoints"
)
@RestController
@RequestMapping("/api/v1/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;


    @Operation(
            summary = "Create Category"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "CREATED"),
            @ApiResponse(responseCode = "409", description = "CONFLICT")
    })
    @PostMapping
    public ResponseEntity<CategoryDTO> createCategory(@RequestBody CategoryDTO categoryDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(categoryService.createCategory(categoryDTO));
    }

    @Operation(
            summary = "get all categories"
    )
    @ApiResponse(responseCode = "200", description = "got all categories")
    @GetMapping
    public ResponseEntity<List<CategoryDTO>> getCategories(){
        return ResponseEntity.status(HttpStatus.OK).body(categoryService.getCategories());
    }


}
