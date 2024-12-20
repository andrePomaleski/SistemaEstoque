package com.andrePomaleski.SistemaEstoque.model.enums;

public enum OrderStatus {
    PENDING,
    WAITING_PAYMENT,
    PACKAGING,
    SHIPPED,
    ARRIVED,
    RETURNED,
    REFUNDED,
    CANCELLED;

    public boolean canTransitionTo(OrderStatus nextStatus) {
        return switch (this) {
            case PENDING -> nextStatus == WAITING_PAYMENT || nextStatus == CANCELLED;
            case WAITING_PAYMENT -> nextStatus == PACKAGING || nextStatus == CANCELLED;
            case PACKAGING -> nextStatus == SHIPPED;
            case SHIPPED -> nextStatus == ARRIVED || nextStatus == RETURNED;
            case ARRIVED -> nextStatus == RETURNED;
            case RETURNED -> nextStatus == REFUNDED;
            default -> false;
        };
    }
}