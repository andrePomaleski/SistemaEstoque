package com.andrePomaleski.SistemaEstoque.controller;

import com.andrePomaleski.SistemaEstoque.dto.OrderResponseDTO;
import com.andrePomaleski.SistemaEstoque.exception.OrderExceptionHandler.OrderNotFoundException;
import com.andrePomaleski.SistemaEstoque.model.Order;
import com.andrePomaleski.SistemaEstoque.model.User;
import com.andrePomaleski.SistemaEstoque.model.enums.UserRole;
import com.andrePomaleski.SistemaEstoque.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    // Endpoint for customers
    @GetMapping
    public ResponseEntity<List<OrderResponseDTO>> getUserOrders(@AuthenticationPrincipal User user) {
        Long userId = user.getId();
        List<Order> orders = orderService.getOrdersByUserId(userId);
        List<OrderResponseDTO> orderResponseDTOS = orders.stream()
                .map(OrderResponseDTO::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(orderResponseDTOS);
    }

    // Endpoint for merchants
    @GetMapping("/merchant")
    public ResponseEntity<List<OrderResponseDTO>> getMerchantOrders(@AuthenticationPrincipal User user) {
        if (!user.getRole().equals(UserRole.MERCHANT)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        Long merchantId = user.getId();
        List<Order> orders = orderService.getOrdersByMerchantId(merchantId);
        List<OrderResponseDTO> orderResponseDTOS = orders.stream()
                .map(OrderResponseDTO::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(orderResponseDTOS);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderResponseDTO> getOrderById(@PathVariable Long id, @AuthenticationPrincipal User user) throws OrderNotFoundException {
        if (user.getRole().equals(UserRole.CUSTOMER)) {
            Order order = orderService.getOrderByIdAndUser(id, user.getId());
            return ResponseEntity.ok(new OrderResponseDTO(order));
        } else if (user.getRole().equals(UserRole.MERCHANT)) {
            Order order = orderService.getOrderByIdAndMerchant(id, user.getId());
            return ResponseEntity.ok(new OrderResponseDTO(order));
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
    }
}


