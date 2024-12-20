package com.andrePomaleski.SistemaEstoque.exception.UserExceptionHandler;

public class UserEmailAlreadyInUseException extends Exception{
    public UserEmailAlreadyInUseException() {
    }

    public UserEmailAlreadyInUseException(String message) {
        super(message);
    }
}
