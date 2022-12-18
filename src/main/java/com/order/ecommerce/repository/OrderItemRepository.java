package com.order.ecommerce.repository;

import com.order.ecommerce.model.OrderItem;
import com.order.ecommerce.model.OrderItemPk;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemRepository extends CrudRepository<OrderItem, OrderItemPk> {
}
