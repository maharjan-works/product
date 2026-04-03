package com.mahajanworks.product.controller;

import com.mahajanworks.product.dto.ProductDTO;
import com.mahajanworks.product.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(
        name = "Product Endpoints",
        description = "for CRUD operations"
)
@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Operation( summary = "add a product")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "created"),
            @ApiResponse(responseCode = "409", description= "category not found")
    })
    @PostMapping
    public ResponseEntity<ProductDTO> addProduct(@RequestBody ProductDTO productDTO){
        return  ResponseEntity.status(HttpStatus.CREATED).body(productService.addProduct(productDTO));
    }

    @Operation(summary = "get all products")
    @ApiResponses({
            @ApiResponse(responseCode = "200",description = "OK")
    })
    @GetMapping
    public ResponseEntity<List<ProductDTO>> getProducts(){
        return ResponseEntity.status(HttpStatus.OK).body(productService.getProducts());
    }
}
