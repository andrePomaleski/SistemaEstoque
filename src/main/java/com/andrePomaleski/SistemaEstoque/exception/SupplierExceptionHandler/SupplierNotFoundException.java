package com.andrePomaleski.SistemaEstoque.exception.SupplierExceptionHandler;

public class SupplierNotFoundException extends Exception{
    public SupplierNotFoundException() {
    }

    public SupplierNotFoundException(String message) {
        super(message);
    }
}
