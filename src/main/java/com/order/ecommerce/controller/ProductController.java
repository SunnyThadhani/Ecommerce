package com.order.ecommerce.controller;


import com.order.ecommerce.dto.ProductDto;
import com.order.ecommerce.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
@Slf4j
public class ProductController {

    @Autowired
    private ProductService productService;


    @GetMapping("/products/{productId}")
    @Operation(summary = "Get a product", description = "Get a product")
    public ResponseEntity<?> getProduct(@PathVariable("productId") String productId){

        try{
           return ResponseEntity.status(HttpStatus.OK).body(productService.getProduct(productId));
        }catch (Exception e){
           return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product with productId "+productId+" not found...");
        }
    }

    @PostMapping("/products")
    @Operation(summary = "Create a product", description = "Create a product")
    public ResponseEntity<?> createProduct(@RequestBody ProductDto productDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.createProduct(productDto));
    }

    @PatchMapping("/products/{productId}")
    @Operation(summary = "Update a Product", description = "Update a Product")
    public ResponseEntity<?> updateProduct(
            @PathVariable("productId") String productId,
            @RequestBody ProductDto productDto){

        try{
            return ResponseEntity.status(HttpStatus.OK).body(productService.updateProduct(productId,productDto));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("productId is not present in the database");
        }

    }
}
