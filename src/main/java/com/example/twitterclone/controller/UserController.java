package com.example.twitterclone.controller;

import com.example.twitterclone.dto.CreateUserRequest;
import com.example.twitterclone.dto.UpdateUserRequest;
import com.example.twitterclone.dto.UserResponse;
import com.example.twitterclone.entity.User;
import com.example.twitterclone.mapper.UserMapper;
import com.example.twitterclone.service.UserService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {


private final UserService userService;
private final UserMapper userMapper;

public UserController(UserService userService, UserMapper userMapper) {
    this.userService = userService;
    this.userMapper = userMapper;
}

// Create a new user
@PostMapping
public ResponseEntity<UserResponse> createUser(
        @Valid @RequestBody CreateUserRequest request) {

    User user = userMapper.toEntity(request);

    User savedUser = userService.createUser(user);

    UserResponse response = userMapper.toResponse(savedUser);

    return ResponseEntity.status(HttpStatus.CREATED).body(response);
}

// Get all users
@GetMapping
public List<UserResponse> getAllUsers() {

    List<User> users = userService.getAllUsers();

    return users.stream()
            .map(userMapper::toResponse)
            .toList();
}

// Get a user by ID
@GetMapping("/{id}")
public UserResponse getUserById(@PathVariable Long id) {

    User user = userService.getUserById(id);

    return userMapper.toResponse(user);
}

// Update an existing user
@PutMapping("/{id}")
public UserResponse updateUser(
        @PathVariable Long id,
        @Valid @RequestBody UpdateUserRequest request) {

    User updatedUser = userMapper.toUpdateEntity(request);

    User savedUser = userService.updateUser(id, updatedUser);

    return userMapper.toResponse(savedUser);
}

// Delete a user
@DeleteMapping("/{id}")
public ResponseEntity<Void> deleteUser(@PathVariable Long id) {

    userService.deleteUser(id);

    return ResponseEntity.noContent().build();
}


}
