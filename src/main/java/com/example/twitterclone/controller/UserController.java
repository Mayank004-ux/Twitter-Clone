package com.example.twitterclone.controller;

import com.example.twitterclone.entity.User;
import com.example.twitterclone.service.UserService;
import com.example.twitterclone.dto.CreateUserRequest;
import com.example.twitterclone.dto.UpdateUserRequest;
import com.example.twitterclone.dto.UserResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
public ResponseEntity<UserResponse> createUser(
        @Valid @RequestBody CreateUserRequest request) {

    User user = new User();

    user.setName(request.getName());
    user.setUsername(request.getUsername());
    user.setEmail(request.getEmail());
    user.setPassword(request.getPassword());

    User savedUser = userService.createUser(user);

    UserResponse response = new UserResponse(
            savedUser.getId(),
            savedUser.getName(),
            savedUser.getUsername(),
            savedUser.getEmail()
    );

    return ResponseEntity.status(HttpStatus.CREATED).body(response);
}

    @GetMapping
public List<UserResponse> getAllUsers() {

    List<User> users = userService.getAllUsers();

    return users.stream()
            .map(user -> new UserResponse(
                    user.getId(),
                    user.getName(),
                    user.getUsername(),
                    user.getEmail()
            ))
            .toList();
}
    @GetMapping("/{id}")
public UserResponse getUserById(@PathVariable Long id) {

    User user = userService.getUserById(id);

    return new UserResponse(
            user.getId(),
            user.getName(),
            user.getUsername(),
            user.getEmail()
    );
}
@PutMapping("/{id}")
public UserResponse updateUser(
        @PathVariable Long id,
        @Valid @RequestBody UpdateUserRequest request) {

    User updatedUser = new User();

    updatedUser.setName(request.getName());
    updatedUser.setUsername(request.getUsername());
    updatedUser.setEmail(request.getEmail());

    User savedUser = userService.updateUser(id, updatedUser);

    return new UserResponse(
            savedUser.getId(),
            savedUser.getName(),
            savedUser.getUsername(),
            savedUser.getEmail()
    );
}
@DeleteMapping("/{id}")
public ResponseEntity<Void> deleteUser(@PathVariable Long id) {

    userService.deleteUser(id);

    return ResponseEntity.noContent().build();
}
}