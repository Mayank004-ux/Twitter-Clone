package com.example.twitterclone.service;

import com.example.twitterclone.entity.User;
import com.example.twitterclone.exception.DuplicateResourceException;
import com.example.twitterclone.exception.ResourceNotFoundException;
import com.example.twitterclone.repository.UserRepository;
import org.springframework.transaction.annotation.Transactional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {


private final UserRepository userRepository;
private final PasswordEncoder passwordEncoder;

public UserService(
        UserRepository userRepository,
        PasswordEncoder passwordEncoder) {

    this.userRepository = userRepository;
    this.passwordEncoder = passwordEncoder;
}

// Create a new user
public User createUser(User user) {

    if (userRepository.existsByUsername(user.getUsername())) {
        throw new DuplicateResourceException(
                "Username is already taken"
        );
    }

    if (userRepository.existsByEmail(user.getEmail())) {
        throw new DuplicateResourceException(
                "Email is already registered"
        );
    }

    user.setPassword(passwordEncoder.encode(user.getPassword()));

    return userRepository.save(user);
}

// Get all users
public List<User> getAllUsers() {

    return userRepository.findAll();
}

// Get user by ID
public User getUserById(Long id) {

    return findUserById(id);
}

// Update an existing user
public User updateUser(Long id, User updatedUser) {

    User existingUser = findUserById(id);

    if (userRepository.existsByUsernameAndIdNot(
            updatedUser.getUsername(), id)) {

        throw new DuplicateResourceException(
                "Username is already taken"
        );
    }

    if (userRepository.existsByEmailAndIdNot(
            updatedUser.getEmail(), id)) {

        throw new DuplicateResourceException(
                "Email is already registered"
        );
    }

    existingUser.setName(updatedUser.getName());
    existingUser.setUsername(updatedUser.getUsername());
    existingUser.setEmail(updatedUser.getEmail());

    return userRepository.save(existingUser);
}

// Delete a user
public void deleteUser(Long id) {

    User existingUser = findUserById(id);

    userRepository.delete(existingUser);
}

// Reusable method to find a user
private User findUserById(Long id) {

    return userRepository.findById(id)
            .orElseThrow(() ->
                    new ResourceNotFoundException(
                            "User not found with id: " + id
                    ));
}
// Follow another user
@Transactional
public void followUser(Long followerId, Long followingId) {

    if (followerId.equals(followingId)) {
        throw new IllegalArgumentException("A user cannot follow themselves");
    }

    User follower = findUserById(followerId);
    User following = findUserById(followingId);

    if (!follower.getFollowing().add(following)) {
        throw new DuplicateResourceException("Already following this user");
    }

    following.getFollowers().add(follower);

    userRepository.save(follower);
}

// Unfollow a user
@Transactional
public void unfollowUser(Long followerId, Long followingId) {

    User follower = findUserById(followerId);
    User following = findUserById(followingId);

    if (!follower.getFollowing().remove(following)) {
        throw new ResourceNotFoundException("You are not following this user");
    }

    following.getFollowers().remove(follower);

    userRepository.save(follower);
}
}
