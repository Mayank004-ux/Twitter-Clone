package com.example.twitterclone.controller;

import com.example.twitterclone.entity.User;
import com.example.twitterclone.service.UserService;

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
public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
    User savedUser = userService.createUser(user);

    savedUser.setPassword(null);

    return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
}

    @GetMapping
    public List<User> getAllUsers() {
        List<User> users = userService.getAllUsers();

        users.forEach(user -> user.setPassword(null));

        return users;
    }
    @GetMapping("/{id}")
public User getUserById(@PathVariable Long id) {
    User user = userService.getUserById(id);

    user.setPassword(null);

    return user;
}
@PutMapping("/{id}")
public User updateUser(@PathVariable Long id, @Valid @RequestBody User updatedUser) {

    User user = userService.updateUser(id, updatedUser);

    user.setPassword(null);

    return user;
}
@DeleteMapping("/{id}")
public ResponseEntity<Void> deleteUser(@PathVariable Long id) {

    userService.deleteUser(id);

    return ResponseEntity.noContent().build();
}
}