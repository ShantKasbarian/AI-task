package com.example.socialmedia.controller;

import com.example.socialmedia.dto.UserDTO;
import com.example.socialmedia.model.User;
import com.example.socialmedia.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public User createUser(@RequestBody UserDTO dto) {
        User user = User.builder()
                .username(dto.getUsername())
                .build();
        return userService.saveUser(user);
    }

    @PostMapping("/{id}/follow/{followId}")
    public void followUser(@PathVariable Long id, @PathVariable Long followId) {
        userService.followUser(id, followId);
    }
}
