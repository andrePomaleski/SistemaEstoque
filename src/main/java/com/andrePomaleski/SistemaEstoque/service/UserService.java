package com.andrePomaleski.SistemaEstoque.service;

import com.andrePomaleski.SistemaEstoque.dto.UserRequestDTO;
import com.andrePomaleski.SistemaEstoque.dto.UserResponseDTO;
import com.andrePomaleski.SistemaEstoque.exception.UserExceptionHandler.UserNotFoundException;
import com.andrePomaleski.SistemaEstoque.model.User;
import com.andrePomaleski.SistemaEstoque.repository.UserRepository;
import com.andrePomaleski.SistemaEstoque.strategy.UserValidationStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private List<UserValidationStrategy> userValidationStrategy;

    public User createUser(UserRequestDTO user) {
        userValidationStrategy.forEach(validate -> validate.validate(user));
        User createdUser = new User(user);
        return userRepository.save(createdUser);
    }

    public User updateUser(Long id, UserRequestDTO updatedUser) {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        userValidationStrategy.forEach(validate -> validate.validate(updatedUser));
        existingUser.setUsername(updatedUser.getUsername());
        existingUser.setEmail(updatedUser.getEmail());
        return userRepository.save(existingUser);
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public void deleteUser(Long id) {
        User user = getUserById(id);
        // If not found will have the exception already on the other method
        userRepository.deleteById(user.getId());
    }
}
