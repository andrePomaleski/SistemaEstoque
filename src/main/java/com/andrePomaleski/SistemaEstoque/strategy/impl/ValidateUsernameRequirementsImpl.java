package com.andrePomaleski.SistemaEstoque.strategy.impl;

import com.andrePomaleski.SistemaEstoque.dto.UserRequestDTO;
import com.andrePomaleski.SistemaEstoque.exception.UserExceptionHandler.UserUsernameMissingRequirementsException;
import com.andrePomaleski.SistemaEstoque.repository.UserRepository;
import com.andrePomaleski.SistemaEstoque.strategy.UserValidationStrategy;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidateUsernameRequirementsImpl implements UserValidationStrategy {
    @Autowired
    private UserRepository userRepository;

    @SneakyThrows
    @Override
    public void validate(UserRequestDTO user) {
        if(!user.getUsername().matches("^[a-zA-Z]+$")){
            throw new UserUsernameMissingRequirementsException("Name must contain only letters");
        }
    }
}
