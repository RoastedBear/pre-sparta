package com.presparta.order.product.dto;

public record ProductCreateRequest(
        String name,
        Long price,
        Long stock
) { }
