package com.presparta.order.order.dto;

public record OrderCreateRequest(
        Long productId,
        Long qty
) {
}
