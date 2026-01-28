package com.presparta.order.product.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "PRODUCTS")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PRODUCT_ID")
    private Long id;

    @Column(name = "PRODUCT_NAME", nullable = false, length = 100)
    private String name;

    @Column(name = "PRODUCT_PRICE", nullable = false)
    private Long price;

    @Column(name = "PRODUCT_STOCK", nullable = false)
    private Long stock;

    @Column(name = "PRODUCT_CREATE_AT")
    private LocalDateTime createdAt;

    @Column(name = "PRODUCT_UPDATE_AT")
    private LocalDateTime updatedAt;

    @Column(name = "PRODUCT_DELETE_AT")
    private LocalDateTime deletedAt;

    protected Product() {}

    public Product(String name, Long price, Long stock) {
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    //상품재고 줄이는 메서드
    public void decreaseStock(Long qty) {
        if (qty <= 0) {
            throw new IllegalArgumentException("수량은 1개 이상이어야 합니다.");
        }
        if (this.stock < qty) {
            throw new IllegalStateException("재고가 부족합니다.");
        }
        this.stock -= qty;
    }

    public void update(String name, Long price, Long stock) {
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.updatedAt = LocalDateTime.now();
    }

    public void delete() {
        this.deletedAt = LocalDateTime.now();
    }

    public Long getId() { return id; }
    public String getName() { return name; }
    public Long getPrice() { return price; }
    public Long getStock() { return stock; }
}
