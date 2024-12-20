package com.andrePomaleski.SistemaEstoque.strategy.impl;

import com.andrePomaleski.SistemaEstoque.dto.UserRequestDTO;
import com.andrePomaleski.SistemaEstoque.exception.UserExceptionHandler.UserUsernameAlreadyInUseException;
import com.andrePomaleski.SistemaEstoque.repository.UserRepository;
import com.andrePomaleski.SistemaEstoque.strategy.UserValidationStrategy;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidateUsernameUniquenessImpl implements UserValidationStrategy {
    // Despite the entity contains columns unique = true, it's better to handle errors
    @Autowired
    private UserRepository userRepository;

    @SneakyThrows
    @Override
    public void validate(UserRequestDTO user) {
        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            throw new UserUsernameAlreadyInUseException("Username already exists");
        }
    }
}