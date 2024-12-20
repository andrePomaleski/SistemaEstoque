package com.andrePomaleski.SistemaEstoque.repository;

import com.andrePomaleski.SistemaEstoque.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}
