package com.example.twitterclone.service;

import com.example.twitterclone.dto.CreateTweetRequest;
import com.example.twitterclone.entity.Tweet;
import com.example.twitterclone.entity.User;
import com.example.twitterclone.exception.ResourceNotFoundException;
import com.example.twitterclone.repository.TweetRepository;
import com.example.twitterclone.repository.UserRepository;
import com.example.twitterclone.dto.UpdateTweetRequest;


import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TweetService {

    private final TweetRepository tweetRepository;
    private final UserRepository userRepository;

    public TweetService(
            TweetRepository tweetRepository,
            UserRepository userRepository) {

        this.tweetRepository = tweetRepository;
        this.userRepository = userRepository;
    }

    // Create a new tweet
    public Tweet createTweet(CreateTweetRequest request) {

        User user = userRepository.findById(request.getUserId())
        .orElseThrow(() ->
                new ResourceNotFoundException(
                        "User not found with ID: " + request.getUserId()
                ));

        Tweet tweet = new Tweet();
        tweet.setContent(request.getContent());
        tweet.setUser(user);

        return tweetRepository.save(tweet);
    }

    // Get all tweets
    public List<Tweet> getAllTweets() {
        return tweetRepository.findAll();
    }

    // Get tweet by ID
    public Tweet getTweetById(Long id) {
    return tweetRepository.findById(id)
            .orElseThrow(() ->
                    new ResourceNotFoundException(
                            "Tweet not found with ID: " + id
                    ));
}
// Update an existing tweet
public Tweet updateTweet(Long id, UpdateTweetRequest request) {

    Tweet tweet = tweetRepository.findById(id)
            .orElseThrow(() ->
                    new ResourceNotFoundException(
                            "Tweet not found with ID: " + id
                    ));

    tweet.setContent(request.getContent());

    return tweetRepository.save(tweet);
}

    // Delete a tweet
    public void deleteTweet(Long id) {

    Tweet tweet = tweetRepository.findById(id)
            .orElseThrow(() ->
                    new ResourceNotFoundException(
                            "Tweet not found with ID: " + id
                    ));

    tweetRepository.delete(tweet);
}
}