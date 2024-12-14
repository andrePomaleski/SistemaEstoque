package com.andrePomaleski.SistemaEstoque.exception.ProductExceptionHandler;

public class ProductNotFoundException extends Exception{
    public ProductNotFoundException() {
    }

    public ProductNotFoundException(String message) {
        super(message);
    }
}
