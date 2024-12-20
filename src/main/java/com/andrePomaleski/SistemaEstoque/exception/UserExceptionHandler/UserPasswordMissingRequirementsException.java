package com.andrePomaleski.SistemaEstoque.exception.UserExceptionHandler;

public class UserPasswordMissingRequirementsException extends Exception{
    public UserPasswordMissingRequirementsException() {
    }

    public UserPasswordMissingRequirementsException(String message) {
        super(message);
    }
}
