package com.order.ecommerce.controller;

import com.order.ecommerce.dto.OrderDto;
import com.order.ecommerce.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
@Slf4j
public class OrderController {

    @Autowired
    OrderService orderService;

    @PostMapping("/orders")
    @Operation(summary = "Create an order", description = "Create an order")
    public ResponseEntity<?> createOrder(@RequestBody OrderDto orderDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(orderService.createOrder(orderDto));
    }

    @GetMapping("/orders/{orderId}")
    @Operation(summary = "Get an order", description = "Get an Order By Id")
    public ResponseEntity<?> findOrderById(@PathVariable(name = "orderId") String orderId){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(orderService.findOrderById(orderId));
        }catch(Exception e) {
            log.error("No order found with orderId {}", orderId);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No order found with orderId " + orderId);
        }
    }

    @PatchMapping("/orders/{orderId}")
    @Operation(summary = "Update an order", description = "Update an Order By Id")
    public void updateOrderStatus(
            @PathVariable("orderId") String orderId,
            @RequestParam(name = "orderStatus") String orderStatus){

        orderService.updateOrderStatus(orderId,orderStatus);
    }
}
