package com.andrePomaleski.SistemaEstoque.exception.UserExceptionHandler;

public class UserUsernameAlreadyInUseException extends Exception{
    public UserUsernameAlreadyInUseException() {
    }

    public UserUsernameAlreadyInUseException(String message) {
        super(message);
    }
}
