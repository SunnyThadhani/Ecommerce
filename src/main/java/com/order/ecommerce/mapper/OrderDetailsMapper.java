package com.order.ecommerce.mapper;

import com.order.ecommerce.dto.AddressDto;
import com.order.ecommerce.dto.OrderItemDto;
import com.order.ecommerce.enums.PaymentStatus;
import com.order.ecommerce.model.Address;
import com.order.ecommerce.model.OrderItem;
import com.order.ecommerce.model.OrderItemPk;
import com.order.ecommerce.model.Payment;
import com.order.ecommerce.repository.AddressRepository;
import com.order.ecommerce.repository.OrderItemRepository;
import com.order.ecommerce.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class OrderDetailsMapper {

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private PaymentRepository paymentRepository;

    public Payment buildAndLoadPayment(Double amount, String paymentMode){
        Payment payment=new Payment();
        payment.setPaymentId(UUID.randomUUID().toString());
        payment.setAmount(amount);
        payment.setPaymentMode(paymentMode);
        payment.setConfirmationNumber(UUID.randomUUID().toString());
        payment.setPaymentStatus(PaymentStatus.PROCESSING.name());
        payment.setCreatedAt(LocalDate.now());

        paymentRepository.save(payment);
        return payment;
    }

    public Address buildAndLoadAddress(AddressDto addressDto){
        Address address=addressDto.toAddressEntity();
        return addressRepository.save(address);
    }

    public List<OrderItem> buildOrderItems(String orderId, List<OrderItemDto> orderItemsDtoList){

       List<OrderItem> orderItems= orderItemsDtoList.stream().map(orderItemDto -> {
           OrderItem orderItem=new OrderItem();
           orderItem.setOrderItemPk(new OrderItemPk(orderItemDto.getProductId(),orderId));
           orderItem.setQuantity(orderItemDto.getQuantity());

           return orderItem;
        }).collect(Collectors.toList());

       return (List<OrderItem>) orderItemRepository.saveAll(orderItems);
    }

}
