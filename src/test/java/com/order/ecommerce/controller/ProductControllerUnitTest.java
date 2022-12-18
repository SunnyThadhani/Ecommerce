package com.order.ecommerce.controller;

import com.order.ecommerce.dto.OrderCreateResponse;
import com.order.ecommerce.dto.OrderDto;
import com.order.ecommerce.dto.ProductDto;
import com.order.ecommerce.model.Order;
import com.order.ecommerce.model.Product;
import com.order.ecommerce.service.ProductService;
import com.order.ecommerce.util.OrderUtil;
import com.order.ecommerce.util.ProductUtil;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProductControllerUnitTest {

    @Mock
    private ProductService productService;

    @InjectMocks
    private ProductController productController;

    private ProductDto productDtoRequest= ProductUtil.createTestProduct();

    private static Product mockProductCreateResponse;

    private static Product mockProductGetResponse;

    static {
        mockProductCreateResponse=new Product("201","kdsk545","Men's shoes","Asian men's shoes",
                765.00,LocalDate.now(),null);
        mockProductGetResponse=ProductUtil.createMockProductResponse();
    }

    @Test
    @DisplayName("Create Product")
    void testCreateProduct(){
        when(productService.createProduct(productDtoRequest))
                .thenReturn(mockProductCreateResponse);

        ResponseEntity<?> actualResponse=productController.createProduct(productDtoRequest);
        Assertions.assertThat(actualResponse).isEqualTo(ResponseEntity.status(HttpStatus.CREATED).body(mockProductCreateResponse));
    }

    @Test
    @DisplayName("Get Product By Id")
    void testGetProduct(){
        when(productService.getProduct("210"))
                .thenReturn(mockProductGetResponse);

        ResponseEntity<?> actualResponse=productController.getProduct("210");
        Assertions.assertThat(actualResponse).isEqualTo(ResponseEntity.status(HttpStatus.OK).body(mockProductGetResponse));
    }
}
