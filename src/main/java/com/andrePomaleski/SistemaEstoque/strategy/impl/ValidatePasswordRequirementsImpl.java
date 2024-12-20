package com.andrePomaleski.SistemaEstoque.strategy.impl;

import com.andrePomaleski.SistemaEstoque.dto.UserRequestDTO;
import com.andrePomaleski.SistemaEstoque.exception.UserExceptionHandler.UserPasswordMissingRequirementsException;
import com.andrePomaleski.SistemaEstoque.repository.UserRepository;
import com.andrePomaleski.SistemaEstoque.strategy.UserValidationStrategy;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidatePasswordRequirementsImpl implements UserValidationStrategy {
    @Autowired
    private UserRepository userRepository;

    @SneakyThrows
    @Override
    public void validate(UserRequestDTO user) {
        if(!user.getPassword().matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[\\W_]).+$")){
            throw new UserPasswordMissingRequirementsException("Password must contain: 1 lowercased letter, 1 uppercased letter, 1 number and 1 special symbol");
        }
    }
}
