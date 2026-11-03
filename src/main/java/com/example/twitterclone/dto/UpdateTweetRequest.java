package com.example.twitterclone.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class UpdateTweetRequest {

    @NotBlank(message = "Tweet content cannot be empty")
    @Size(max = 280, message = "Tweet cannot exceed 280 characters")
    private String content;

    public UpdateTweetRequest() {
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}