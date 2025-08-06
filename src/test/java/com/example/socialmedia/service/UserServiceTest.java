package com.example.socialmedia.service;

import com.example.socialmedia.model.User;
import com.example.socialmedia.repository.UserRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.HashSet;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    private User user1;
    private User user2;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        user1 = User.builder().id(1L).username("user1").build();
        user1.setFollowers(new HashSet<>());
        user1.setFollowing(new HashSet<>());

        user2 = User.builder().id(2L).username("user2").build();
        user2.setFollowers(new HashSet<>());
        user2.setFollowing(new HashSet<>());
    }

    @Test
    void saveUser_ShouldReturnSavedUser() {
        when(userRepository.save(user1)).thenReturn(user1);

        User result = userService.saveUser(user1);

        assertEquals("user1", result.getUsername());
        verify(userRepository, times(1)).save(user1);
    }

    @Test
    void getUser_ShouldReturnUserOptional() {
        when(userRepository.findById(1L)).thenReturn(Optional.of(user1));

        Optional<User> result = userService.getUser(1L);

        assertTrue(result.isPresent());
        assertEquals("user1", result.get().getUsername());
    }

    @Test
    void followUser_ShouldAddFollowerAndFollowing() {
        when(userRepository.findById(1L)).thenReturn(Optional.of(user1));
        when(userRepository.findById(2L)).thenReturn(Optional.of(user2));

        userService.followUser(1L, 2L);

        assertTrue(user1.getFollowing().contains(user2));
        assertTrue(user2.getFollowers().contains(user1));
        verify(userRepository, times(2)).save(any(User.class));
    }
}
