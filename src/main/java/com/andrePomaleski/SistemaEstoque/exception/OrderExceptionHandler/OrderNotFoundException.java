package com.andrePomaleski.SistemaEstoque.exception.OrderExceptionHandler;

public class OrderNotFoundException extends Exception{
    public OrderNotFoundException() {
    }

    public OrderNotFoundException(String message) {
        super(message);
    }
}
