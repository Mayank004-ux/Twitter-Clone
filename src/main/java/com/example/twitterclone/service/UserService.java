package com.example.twitterclone.service;

import com.example.twitterclone.entity.User;
import com.example.twitterclone.exception.ResourceNotFoundException;
import com.example.twitterclone.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {


private final UserRepository userRepository;

public UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
}

public User createUser(User user) {
    return userRepository.save(user);
}

public List<User> getAllUsers() {
    return userRepository.findAll();
}

public User getUserById(Long id) {
    return userRepository.findById(id)
            .orElseThrow(() ->
                    new ResourceNotFoundException("User not found with id: " + id));
}

public User updateUser(Long id, User updatedUser) {

    User existingUser = userRepository.findById(id)
            .orElseThrow(() ->
                    new ResourceNotFoundException("User not found with id: " + id));

    existingUser.setName(updatedUser.getName());
    existingUser.setUsername(updatedUser.getUsername());
    existingUser.setEmail(updatedUser.getEmail());

    return userRepository.save(existingUser);
}

public void deleteUser(Long id) {

    User existingUser = userRepository.findById(id)
            .orElseThrow(() ->
                    new ResourceNotFoundException("User not found with id: " + id));

    userRepository.delete(existingUser);
}


}
