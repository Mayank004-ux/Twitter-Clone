package com.example.twitterclone;

import com.example.twitterclone.mapper.UserMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class TwittercloneApplication {

public static void main(String[] args) {
    SpringApplication.run(TwittercloneApplication.class, args);
}

@Bean
public UserMapper userMapper() {
    return new UserMapper();
}


}
