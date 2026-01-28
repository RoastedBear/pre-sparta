package com.presparta.order.order.entity;

import com.presparta.order.product.entity.Product;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "ORDERS")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ORDER_ID")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PRODUCT_ID", nullable = false)
    private Product product;

    @Column(name = "ORDER_QTY", nullable = false)
    private Long qty;

    @Column(name = "ORDER_CREATED_AT", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "ORDER_STATUS", nullable = false, length = 10)
    private String status;

    @Column(name = "TOTAL_PRICE", nullable = false)
    private Long totalPrice;

    protected Order() {}

    public Order(Product product, Long qty) {
        this.product = product;
        this.qty = qty;
        this.status = "ORDER";
        this.totalPrice = product.getPrice() * qty;
        this.createdAt = LocalDateTime.now();
    }

    // getters
    public Long getId() { return id; }
    public Product getProduct() { return product; }
    public Long getQty() { return qty; }
    public String getStatus() { return status; }
    public Long getTotalPrice() { return totalPrice; }
}
