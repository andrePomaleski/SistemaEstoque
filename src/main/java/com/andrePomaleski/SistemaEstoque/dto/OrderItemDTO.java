package com.andrePomaleski.SistemaEstoque.dto;

import com.andrePomaleski.SistemaEstoque.model.OrderItem;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemDTO {
    private String productName;
    private Integer quantity;
    private Double price;

    public OrderItemDTO(OrderItem orderItem) {
        this.productName = orderItem.getProductName();
        this.quantity = orderItem.getQuantity();
        this.price = orderItem.getPrice();
    }
}
