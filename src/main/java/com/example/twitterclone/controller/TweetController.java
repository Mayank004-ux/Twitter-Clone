package com.example.twitterclone.controller;

import com.example.twitterclone.dto.CreateTweetRequest;
import com.example.twitterclone.dto.TweetResponse;
import com.example.twitterclone.entity.Tweet;
import com.example.twitterclone.service.TweetService;
import com.example.twitterclone.dto.UpdateTweetRequest;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/tweets")
public class TweetController {

    private final TweetService tweetService;

    public TweetController(TweetService tweetService) {
        this.tweetService = tweetService;
    }

    // Create a new tweet
    @PostMapping
    public ResponseEntity<TweetResponse> createTweet(
          @Valid  @RequestBody CreateTweetRequest request) {

        Tweet savedTweet = tweetService.createTweet(request);

        TweetResponse response = new TweetResponse(
                savedTweet.getId(),
                savedTweet.getContent(),
                savedTweet.getUser().getId(),
                savedTweet.getUser().getUsername()
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // Get all tweets
    @GetMapping
    public List<TweetResponse> getAllTweets() {

        return tweetService.getAllTweets()
                .stream()
                .map(tweet -> new TweetResponse(
                        tweet.getId(),
                        tweet.getContent(),
                        tweet.getUser().getId(),
                        tweet.getUser().getUsername()
                ))
                .toList();
    }

    // Get tweet by ID
    @GetMapping("/{id}")
    public TweetResponse getTweetById(@PathVariable Long id) {

        Tweet tweet = tweetService.getTweetById(id);

        return new TweetResponse(
                tweet.getId(),
                tweet.getContent(),
                tweet.getUser().getId(),
                tweet.getUser().getUsername()
        );
    }
    // Update an existing tweet
@PutMapping("/{id}")
public TweetResponse updateTweet(
        @PathVariable Long id,
        @Valid @RequestBody UpdateTweetRequest request) {

    Tweet updatedTweet = tweetService.updateTweet(id, request);

    return new TweetResponse(
            updatedTweet.getId(),
            updatedTweet.getContent(),
            updatedTweet.getUser().getId(),
            updatedTweet.getUser().getUsername()
    );
}

    // Delete a tweet
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTweet(@PathVariable Long id) {

        tweetService.deleteTweet(id);

        return ResponseEntity.noContent().build();
    }
}