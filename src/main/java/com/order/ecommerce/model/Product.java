package com.order.ecommerce.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "ecommerce_product")
@Data
public class Product implements Serializable {

    @Id
    @Column(name = "product_id", nullable = false, unique = true)
    String productId;

    @Column(name = "sku", nullable = false)
    String sku;

    @Column(name = "title", nullable = false)
    String title;

    @Column(name = "description", nullable = false)
    String description;

    @Column(name = "price", nullable = false)
    Double price;

    @Column(name = "createdAt", nullable = false)
    LocalDate createdAt;

    @OneToMany(targetEntity = OrderItem.class, fetch = FetchType.LAZY, mappedBy = "product")
    private List<OrderItem> orderItems;

    public Product(String productId, String sku, String title, String description, Double price, LocalDate createdAt, List<OrderItem> orderItems) {
        this.productId = productId;
        this.sku = sku;
        this.title = title;
        this.description = description;
        this.price = price;
        this.createdAt = createdAt;
        this.orderItems = orderItems;
    }

    public Product() {
    }
}
