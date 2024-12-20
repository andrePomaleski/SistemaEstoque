package com.andrePomaleski.SistemaEstoque.repository;

import com.andrePomaleski.SistemaEstoque.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByUserId(Long userId);
    Optional<Order> findByIdAndUserId(Long orderId, Long userId);
    List<Order> findByMerchantId(Long merchantId);
    Optional<Order> findByIdAndMerchantId(Long orderId, Long merchantId);
}
