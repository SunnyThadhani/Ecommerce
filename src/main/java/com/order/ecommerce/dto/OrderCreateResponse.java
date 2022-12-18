package com.order.ecommerce.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderCreateResponse {

    @NonNull private String orderId;
    @NonNull private String orderStatus;
}
