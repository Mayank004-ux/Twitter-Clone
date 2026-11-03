package com.example.twitterclone.mapper;

import com.example.twitterclone.dto.CreateUserRequest;
import com.example.twitterclone.dto.UpdateUserRequest;
import com.example.twitterclone.dto.UserResponse;
import com.example.twitterclone.entity.User;

import org.springframework.stereotype.Component;

@Component
public class UserMapper {


public User toEntity(CreateUserRequest request) {

    User user = new User();

    user.setName(request.getName());
    user.setUsername(request.getUsername());
    user.setEmail(request.getEmail());
    user.setPassword(request.getPassword());

    return user;
}

public User toUpdateEntity(UpdateUserRequest request) {

    User user = new User();

    user.setName(request.getName());
    user.setUsername(request.getUsername());
    user.setEmail(request.getEmail());

    return user;
}

public UserResponse toResponse(User user) {

    return new UserResponse(
            user.getId(),
            user.getName(),
            user.getUsername(),
            user.getEmail()
    );
}


}
