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
import jakarta.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.JoinTable;
import jakarta.persistence.JoinColumn;
import java.util.HashSet;
import java.util.Set;
import jakarta.persistence.CascadeType;

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

@OneToMany(
    mappedBy = "user",
    cascade = CascadeType.ALL,
    orphanRemoval = true
)
private List<Tweet> tweets = new ArrayList<>();

@ManyToMany
@JoinTable(
    name = "user_followers",
    joinColumns = @JoinColumn(name = "follower_id"),
    inverseJoinColumns = @JoinColumn(name = "following_id")
)
private Set<User> following = new HashSet<>();

@ManyToMany(mappedBy = "following")
private Set<User> followers = new HashSet<>();

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
public List<Tweet> getTweets() {
    return tweets;
}

public void setTweets(List<Tweet> tweets) {
    this.tweets = tweets;
}
public Set<User> getFollowing() {
    return following;
}

public void setFollowing(Set<User> following) {
    this.following = following;
}

public Set<User> getFollowers() {
    return followers;
}

public void setFollowers(Set<User> followers) {
    this.followers = followers;
}
}
