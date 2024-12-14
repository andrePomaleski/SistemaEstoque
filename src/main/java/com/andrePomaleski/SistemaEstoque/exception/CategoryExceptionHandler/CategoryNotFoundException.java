package com.andrePomaleski.SistemaEstoque.exception.CategoryExceptionHandler;

public class CategoryNotFoundException extends Exception{
    public CategoryNotFoundException() {
    }

    public CategoryNotFoundException(String message) {
        super(message);
    }
}
