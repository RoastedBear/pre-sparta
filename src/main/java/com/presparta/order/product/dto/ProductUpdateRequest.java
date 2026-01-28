package com.presparta.order.product.dto;

public record ProductUpdateRequest(
        String name,
        Long price,
        Long stock
) {
}
