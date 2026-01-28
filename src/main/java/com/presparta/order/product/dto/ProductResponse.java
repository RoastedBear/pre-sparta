package com.presparta.order.product.dto;

import com.presparta.order.product.entity.Product;

public record ProductResponse(
        Long id,
        String name,
        Long price,
        Long stock
) {
    public static ProductResponse from(Product product){
        return new ProductResponse(
                product.getId(),
                product.getName(),
                product.getPrice(),
                product.getStock()
        );
    }
}
