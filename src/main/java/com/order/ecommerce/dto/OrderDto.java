package com.order.ecommerce.dto;

import com.order.ecommerce.enums.OrderStatus;
import com.order.ecommerce.mapper.OrderDetailsMapper;
import com.order.ecommerce.model.Order;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto {

    @NonNull private String customerId;
    @NonNull private Double subTotal;
    @NonNull private Double totalAmt;
    @NonNull private Double tax;
    @NonNull private Double shippingCharges;
    @NonNull private String title;
    @NonNull private String shippingMode;

    @NonNull private Double amount;
    @NonNull private String paymentMode;

    @NonNull private AddressDto billingAddress;
    @NonNull private AddressDto shippingAddress;

    @NonNull private List<OrderItemDto> orderItems;
    public Order toOrderEntity(String orderId,OrderDto orderDto,OrderDetailsMapper orderDetailsMapper){

        Order order=new Order();
        order.setOrderId(orderId);
        order.setOrderStatus(String.valueOf(OrderStatus.PROCESSING));
        order.setCustomerId(orderDto.getCustomerId());
        order.setSubTotal(orderDto.getSubTotal());
        order.setTotalAmt(orderDto.getTotalAmt());
        order.setTax(orderDto.getTax());
        order.setShippingCharges(orderDto.getShippingCharges());
        order.setTitle(orderDto.getTitle());
        order.setShippingMode(orderDto.getShippingMode());
        order.setCreatedAt(LocalDateTime.now());
        order.setPayment(orderDetailsMapper.buildAndLoadPayment(orderDto.getAmount(),orderDto.getPaymentMode()));
        order.setBillingAddress(orderDetailsMapper.buildAndLoadAddress(orderDto.getBillingAddress()));
        order.setShippingAddress(orderDetailsMapper.buildAndLoadAddress(orderDto.getShippingAddress()));

        return order;
    }
}
