package com.presparta.order.order.repository;

import com.presparta.order.order.entity.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long> {

    @EntityGraph(attributePaths = "product")
    Optional<Order> findById(Long id);

    @EntityGraph(attributePaths = "product")
    Page<Order> findAll(Pageable pageable);
}