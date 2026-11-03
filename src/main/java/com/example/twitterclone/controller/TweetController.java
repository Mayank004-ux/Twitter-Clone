package com.example.twitterclone.controller;

import com.example.twitterclone.dto.CreateTweetRequest;
import com.example.twitterclone.dto.UpdateTweetRequest;
import com.example.twitterclone.dto.TweetResponse;
import com.example.twitterclone.entity.Tweet;
import com.example.twitterclone.service.TweetService;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
        @Valid @RequestBody CreateTweetRequest request) {

    Tweet savedTweet = tweetService.createTweet(request);

    return ResponseEntity.status(HttpStatus.CREATED)
            .body(toResponse(savedTweet));
}

// Get all tweets
@GetMapping
public List<TweetResponse> getAllTweets() {

    return tweetService.getAllTweets()
            .stream()
            .map(this::toResponse)
            .toList();
}

// Get tweet by ID
@GetMapping("/{id}")
public TweetResponse getTweetById(@PathVariable Long id) {

    Tweet tweet = tweetService.getTweetById(id);

    return toResponse(tweet);
}

// Update an existing tweet
@PutMapping("/{id}")
public TweetResponse updateTweet(
        @PathVariable Long id,
        @Valid @RequestBody UpdateTweetRequest request) {

    Tweet updatedTweet = tweetService.updateTweet(id, request);

    return toResponse(updatedTweet);
}

// Delete a tweet
@DeleteMapping("/{id}")
public ResponseEntity<Void> deleteTweet(@PathVariable Long id) {

    tweetService.deleteTweet(id);

    return ResponseEntity.noContent().build();
}

// Convert Tweet entity into TweetResponse DTO
private TweetResponse toResponse(Tweet tweet) {

    return new TweetResponse(
            tweet.getId(),
            tweet.getContent(),
            tweet.getUser().getId(),
            tweet.getUser().getUsername()
    );
}


}
