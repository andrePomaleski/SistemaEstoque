package com.andrePomaleski.SistemaEstoque.exception.UserExceptionHandler;

public class UserNotFoundException extends Exception{
    public UserNotFoundException() {
    }

    public UserNotFoundException(String message) {
        super(message);
    }
}
