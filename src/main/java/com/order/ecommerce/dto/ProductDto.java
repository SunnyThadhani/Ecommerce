package com.order.ecommerce.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {
    @NonNull private String productId;
    @NonNull private String sku;
    @NonNull private String title;
    @NonNull private String description;
    @NonNull private Double price;
}
