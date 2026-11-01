package com.example.twitterclone.dto;

public class CreateTweetRequest {

    private String content;
    private Long userId;

    public CreateTweetRequest() {
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}