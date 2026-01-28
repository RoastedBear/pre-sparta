package com.presparta.order.order.dto;

import com.presparta.order.order.entity.Order;

public record OrderResponse(
        Long orderId,
        Long productId,
        String productName,
        Long qty,
        Long totalPrice,
        String status
) {
    public static OrderResponse from(Order order) {
        return new OrderResponse(
                order.getId(),
                order.getProduct().getId(),
                order.getProduct().getName(),
                order.getQty(),
                order.getTotalPrice(),
                order.getStatus()
        );
    }
}

