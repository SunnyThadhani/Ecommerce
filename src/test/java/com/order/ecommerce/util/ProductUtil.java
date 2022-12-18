package com.order.ecommerce.util;

import com.order.ecommerce.dto.ProductDto;
import com.order.ecommerce.model.Product;

import java.time.LocalDate;

public class ProductUtil {
//    public static ProductDto createTestProduct() {
//    }

    public static Product createMockProductResponse() {
        Product product=new Product();

        product.setProductId("210");
        product.setSku("kdsk545");
        product.setTitle("Men's shoes");
        product.setDescription("Asian men's shoes");
        product.setPrice(765.00);
        product.setCreatedAt(LocalDate.now());
        product.setOrderItems(null);

        return product;
    }

    public static ProductDto createTestProduct() {
        return new ProductDto();
    }
}
