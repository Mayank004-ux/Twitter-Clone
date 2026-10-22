package com.example.twitterclone.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import jakarta.persistence.Column;

@Entity
@Table(name = "users")
public class User {


@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;

@NotBlank(message = "Name cannot be empty")
private String name;

@NotBlank(message = "Username cannot be empty")
@Size(min = 3, max = 20, message = "Username must be between 3 and 20 characters")

@Column(unique = true)
private String username;

@NotBlank(message = "Email cannot be empty")
@Email(message = "Please provide a valid email address")

@Column(unique = true)
private String email;

@NotBlank(message = "Password cannot be empty")
private String password;

public User() {
}

public Long getId() {
    return id;
}

public String getName() {
    return name;
}

public void setName(String name) {
    this.name = name;
}

public String getUsername() {
    return username;
}

public void setUsername(String username) {
    this.username = username;
}

public String getEmail() {
    return email;
}

public void setEmail(String email) {
    this.email = email;
}

public String getPassword() {
    return password;
}

public void setPassword(String password) {
    this.password = password;
}


}
