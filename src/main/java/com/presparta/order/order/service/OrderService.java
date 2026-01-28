package com.presparta.order.order.service;

import com.presparta.order.order.entity.Order;
import com.presparta.order.order.repository.OrderRepository;
import com.presparta.order.product.entity.Product;
import com.presparta.order.product.service.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class OrderService {

    private final OrderRepository orderRepository;
    private final ProductService productService;

    public OrderService(OrderRepository orderRepository, ProductService productService){
        this.orderRepository = orderRepository;
        this.productService = productService;
    }

    public Order create(Long productId, Long qty){
        Product product = productService.checkProduct(productId);

        product.decreaseStock(qty);

        Order order = new Order(product, qty);
        return orderRepository.save(order);
    }

    @Transactional(readOnly = true)
    public Order get(Long orderId){
        return orderRepository.findById(orderId)
                .orElseThrow(() -> new IllegalArgumentException("주문이 존재하지 않습니다."));
    }

    @Transactional(readOnly = true)
    public Page<Order> getPage(Pageable pageable) {
        return orderRepository.findAll(pageable);
    }


}
