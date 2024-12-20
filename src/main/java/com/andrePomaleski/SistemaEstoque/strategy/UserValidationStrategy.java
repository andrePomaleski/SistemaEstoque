package com.andrePomaleski.SistemaEstoque.strategy;

import com.andrePomaleski.SistemaEstoque.dto.UserRequestDTO;

public interface UserValidationStrategy {

    void validate(UserRequestDTO user);
}
