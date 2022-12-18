package com.order.ecommerce.util;

import com.order.ecommerce.dto.AddressDto;
import com.order.ecommerce.dto.OrderDto;
import com.order.ecommerce.dto.OrderItemDto;
import com.order.ecommerce.enums.PaymentMode;
import com.order.ecommerce.enums.ShippingMode;
import com.order.ecommerce.model.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class OrderUtil {

    public static OrderDto createTestOrder() {
        OrderDto orderDto = new OrderDto();
        orderDto.setCustomerId("1");
        orderDto.setSubTotal(6.0);
        orderDto.setTotalAmt(10.0);
        orderDto.setTax(2.0);
        orderDto.setShippingCharges(2.0);
        orderDto.setTitle("test");
        orderDto.setShippingMode(ShippingMode.DELIVERY.name());
        orderDto.setAmount(10.0);
        orderDto.setPaymentMode(PaymentMode.CREDIT.name());
        orderDto.setBillingAddress(createAddress());
        orderDto.setShippingAddress(createAddress());
        orderDto.setOrderItems(Arrays.asList(
                new OrderItemDto("101", "10"),
                new OrderItemDto("102", "10")
        ));

        return orderDto;
    }

    private static AddressDto createAddress() {
        AddressDto addressDto=new AddressDto();
        addressDto.setAddress1("3755 M lane");
        addressDto.setAddress2("Apt 311");
        addressDto.setCity("Aurora");
        addressDto.setState("IL");
        addressDto.setZip("60504");
        addressDto.setEmail("test.gmail.com");
        addressDto.setPhone("1234567890");

        return addressDto;
    }

    public static Order createMockOrderResponse(){

        Payment payment=new Payment();
        Address billingAddress=new Address();
        Address shippingAddress=new Address();
        List<OrderItem> orderItems=new ArrayList<>();
        LocalDateTime dateTime= LocalDateTime.parse("2022-10-17T11:31:27.771692");

        Order order =new Order();
        order.setOrderId("2e99fe21-2243-4004-9640-e992bbcc5040");
        order.setOrderStatus("PROCESSING");
        order.setCustomerId("2");
        order.setSubTotal(6.0);
        order.setTotalAmt(10.0);
        order.setTax(2.0);
        order.setShippingCharges(2.0);
        order.setTitle("testProduct");
        order.setShippingMode("delivery");
        order.setCreatedAt(dateTime);
        order.setPayment(payment);
        order.setBillingAddress(billingAddress);
        order.setShippingAddress(shippingAddress);
        order.setOrderItems(orderItems);

        return order;
    }

}
