package com.example.socialmedia.service;

import com.example.socialmedia.model.User;
import com.example.socialmedia.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public Optional<User> getUser(Long id) {
        return userRepository.findById(id);
    }

    public void followUser(Long userId, Long followId) {
        User user = userRepository.findById(userId).orElseThrow();
        User toFollow = userRepository.findById(followId).orElseThrow();
        user.getFollowing().add(toFollow);
        toFollow.getFollowers().add(user);
        userRepository.save(user);
        userRepository.save(toFollow);
    }
}
