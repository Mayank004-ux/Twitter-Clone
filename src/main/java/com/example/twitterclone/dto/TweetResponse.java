package com.example.twitterclone.dto;

public class TweetResponse {

    private Long id;
    private String content;
    private Long userId;
    private String username;

    public TweetResponse() {
    }

    public TweetResponse(Long id, String content, Long userId, String username) {
        this.id = id;
        this.content = content;
        this.userId = userId;
        this.username = username;
    }

    public Long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public Long getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }
}