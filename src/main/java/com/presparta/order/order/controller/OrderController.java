package com.presparta.order.order.controller;

import com.presparta.order.order.dto.OrderCreateRequest;
import com.presparta.order.order.dto.OrderResponse;
import com.presparta.order.order.entity.Order;
import com.presparta.order.order.service.OrderService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService){
        this.orderService = orderService;
    }

    @PostMapping
    public OrderResponse create(@RequestBody OrderCreateRequest request){
        Order order = orderService.create(request.productId(), request.qty());
        return OrderResponse.from(order);
    }

    @GetMapping("/{orderId}")
    public OrderResponse get(@PathVariable Long orderId){
        return OrderResponse.from(orderService.get(orderId));
    }

    @GetMapping
    public Page<OrderResponse> getPage(Pageable pageable){
        return orderService.getPage(pageable).map(OrderResponse::from);
    }
}
