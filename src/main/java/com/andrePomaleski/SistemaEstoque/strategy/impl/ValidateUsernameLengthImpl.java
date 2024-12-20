package com.andrePomaleski.SistemaEstoque.strategy.impl;

import com.andrePomaleski.SistemaEstoque.dto.UserRequestDTO;
import com.andrePomaleski.SistemaEstoque.exception.UserExceptionHandler.UserUsernameLengthException;
import com.andrePomaleski.SistemaEstoque.repository.UserRepository;
import com.andrePomaleski.SistemaEstoque.strategy.UserValidationStrategy;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidateUsernameLengthImpl implements UserValidationStrategy {
    @Autowired
    private UserRepository userRepository;

    @SneakyThrows
    @Override
    public void validate(UserRequestDTO user) {
        if(user.getUsername().length() < 8 || user.getUsername().length() > 24) {
            throw new UserUsernameLengthException("Your username shall be at least size 8 and maximium 24");
        }
    }
}