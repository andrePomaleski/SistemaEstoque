package com.andrePomaleski.SistemaEstoque.strategy.impl;

import com.andrePomaleski.SistemaEstoque.dto.UserRequestDTO;
import com.andrePomaleski.SistemaEstoque.exception.UserExceptionHandler.UserPasswordLengthException;
import com.andrePomaleski.SistemaEstoque.strategy.UserValidationStrategy;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

@Component
public class ValidatePasswordLengthImpl implements UserValidationStrategy {
    @SneakyThrows
    @Override
    public void validate(UserRequestDTO user){
        if(user.getPassword().length() < 8 || user.getPassword().length() > 60) {
            throw new UserPasswordLengthException("Password must contain at least 8 size long, and 60 maximium");
        }
    }
}