package com.andrePomaleski.SistemaEstoque.service;

import com.andrePomaleski.SistemaEstoque.exception.OrderExceptionHandler.OrderNotFoundException;
import com.andrePomaleski.SistemaEstoque.model.Order;
import com.andrePomaleski.SistemaEstoque.model.enums.UserRole;
import com.andrePomaleski.SistemaEstoque.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    public List<Order> getOrdersByUserId(Long userId) {
        return orderRepository.findByUserId(userId);
    }

    public Order getOrderByIdAndUser(Long orderId, Long userId) throws OrderNotFoundException {
        return orderRepository.findByIdAndUserId(orderId, userId)
                .orElseThrow(() -> new OrderNotFoundException("Order not found or not owned by the user"));
    }

    public List<Order> getOrdersByMerchantId(Long merchantId) {
        return orderRepository.findByMerchantId(merchantId);
    }

    public Order getOrderByIdAndMerchant(Long orderId, Long merchantId) throws OrderNotFoundException {
        return orderRepository.findByIdAndMerchantId(orderId, merchantId)
                .orElseThrow(() -> new OrderNotFoundException("Order not found or not associated with the merchant"));
    }
}
