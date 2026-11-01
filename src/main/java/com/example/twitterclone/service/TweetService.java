package com.example.twitterclone.service;

import com.example.twitterclone.dto.CreateTweetRequest;
import com.example.twitterclone.entity.Tweet;
import com.example.twitterclone.entity.User;
import com.example.twitterclone.repository.TweetRepository;
import com.example.twitterclone.repository.UserRepository;

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
                        new RuntimeException("User not found"));

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
                        new RuntimeException("Tweet not found with ID: " + id));
    }

    // Delete a tweet
    public void deleteTweet(Long id) {
        tweetRepository.deleteById(id);
    }
}