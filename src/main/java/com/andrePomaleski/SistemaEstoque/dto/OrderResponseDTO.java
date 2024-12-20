package com.andrePomaleski.SistemaEstoque.dto;

import com.andrePomaleski.SistemaEstoque.model.Order;
import com.andrePomaleski.SistemaEstoque.model.OrderItem;
import com.andrePomaleski.SistemaEstoque.model.User;
import com.andrePomaleski.SistemaEstoque.model.enums.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderResponseDTO {

    private Long id;
    private Long userId;
    private Long merchantId; // Add the merchant ID
    private Date orderDate;
    private OrderStatus status;
    private List<OrderItemDTO> orderItems;
    private String deliveryAddress;

    public OrderResponseDTO(Order order) {
        this.id = order.getId();
        this.userId = order.getUser().getId();  // Assuming the user ID is needed in the response
        this.merchantId = order.getMerchant() != null ? order.getMerchant().getId() : null; // Map the merchant ID
        this.orderDate = order.getOrderDate();
        this.status = order.getStatus();
        this.deliveryAddress = order.getDeliveryAddress();
        this.orderItems = order.getOrderItems().stream()
                .map(OrderItemDTO::new)
                .collect(Collectors.toList());
    }
}
