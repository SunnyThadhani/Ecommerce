package com.order.ecommerce.controller;

import com.order.ecommerce.dto.OrderCreateResponse;
import com.order.ecommerce.dto.OrderDto;
import com.order.ecommerce.model.Order;
import com.order.ecommerce.service.OrderService;
import com.order.ecommerce.util.OrderUtil;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class OrderControllerUnitTest {

    @Mock
    private OrderService orderService;

    @InjectMocks
    private OrderController orderController;

    private OrderDto orderDtoRequest= OrderUtil.createTestOrder();

    private static OrderCreateResponse mockOrderCreateResponse;
    private static Order mockOrderGetResponse;

    static {
        mockOrderCreateResponse=new OrderCreateResponse("2e99fe21-2243-4004-9640-e992bbcc5040","PROCESSING");
        mockOrderGetResponse= OrderUtil.createMockOrderResponse();
    }

    @Test
    @DisplayName("Create Order")
    void testCreateOrder(){

        when(orderService.createOrder(orderDtoRequest))
                .thenReturn(mockOrderCreateResponse);

        ResponseEntity<?> actualResponse=orderController.createOrder(orderDtoRequest);
        Assertions.assertThat(actualResponse).isEqualTo(ResponseEntity.status(HttpStatus.CREATED).body(mockOrderCreateResponse));
    }

    @Test
    @DisplayName("Get Order By Id")
    void testGetOrder(){
        when(orderService.findOrderById("2e99fe21-2243-4004-9640-e992bbcc5040"))
                .thenReturn(mockOrderGetResponse);

        ResponseEntity<?> actualResponse=orderController.findOrderById("2e99fe21-2243-4004-9640-e992bbcc5040");
        Assertions.assertThat(actualResponse).isEqualTo(ResponseEntity.status(HttpStatus.OK).body(mockOrderGetResponse));
    }

}
