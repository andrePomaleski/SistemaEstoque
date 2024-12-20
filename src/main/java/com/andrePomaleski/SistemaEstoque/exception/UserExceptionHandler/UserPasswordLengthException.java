package com.andrePomaleski.SistemaEstoque.exception.UserExceptionHandler;

public class UserPasswordLengthException extends Exception{
    public UserPasswordLengthException() {
    }

    public UserPasswordLengthException(String message) {
        super(message);
    }
}
