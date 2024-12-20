package com.andrePomaleski.SistemaEstoque.controller;

import com.andrePomaleski.SistemaEstoque.dto.UserRequestDTO;
import com.andrePomaleski.SistemaEstoque.dto.UserResponseDTO;
import com.andrePomaleski.SistemaEstoque.exception.UserExceptionHandler.UserNotFoundException;
import com.andrePomaleski.SistemaEstoque.model.User;
import com.andrePomaleski.SistemaEstoque.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<UserResponseDTO>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        List<UserResponseDTO> userResponseDTOs = users.stream().map(UserResponseDTO::new).toList();
        return ResponseEntity.ok(userResponseDTOs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> getUserById(@PathVariable Long id) throws UserNotFoundException {
        User user = userService.getUserById(id);
        UserResponseDTO response = new UserResponseDTO(user);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<UserResponseDTO> createUser(@RequestBody UserRequestDTO userRequestDTO) {
        User user = userService.createUser(userRequestDTO);
        UserResponseDTO response = new UserResponseDTO(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDTO> updateUser(@PathVariable Long id,
                                                      @RequestBody UserRequestDTO userRequestDTO) throws UserNotFoundException {
        User user = userService.updateUser(id, userRequestDTO);
        UserResponseDTO response = new UserResponseDTO(user);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) throws UserNotFoundException {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
