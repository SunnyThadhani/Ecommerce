package com.order.ecommerce.service;

import com.order.ecommerce.dto.OrderCreateResponse;
import com.order.ecommerce.dto.OrderDto;
import com.order.ecommerce.mapper.OrderDetailsMapper;
import com.order.ecommerce.model.Order;
import com.order.ecommerce.model.OrderItem;
import com.order.ecommerce.repository.OrderItemRepository;
import com.order.ecommerce.repository.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderDetailsMapper orderDetailsMapper;
    @Autowired
    private OrderItemRepository orderItemRepository;

    @Transactional
    public OrderCreateResponse createOrder(OrderDto orderDto) {
        log.info("Creating Order for customer {}", orderDto.getCustomerId());

        Order savedOrder = orderRepository.save(orderDto.toOrderEntity(UUID.randomUUID().toString(),orderDto,orderDetailsMapper));

        List<OrderItem> orderItemList=orderDetailsMapper.buildOrderItems(savedOrder.getOrderId(),orderDto.getOrderItems());
        orderItemRepository.saveAll(orderItemList);

        //Always return a dto - Need to map entity to dto
        return new OrderCreateResponse(savedOrder.getOrderId(), savedOrder.getOrderStatus());
    }

    public Order findOrderById(String orderId) {
        return orderRepository.findById(orderId).orElseThrow();
    }

    public void updateOrderStatus(String orderId, String orderStatus) {
        Order order=orderRepository.findById(orderId).orElseThrow();
        order.setOrderStatus(orderStatus);

        orderRepository.save(order);
    }
}
