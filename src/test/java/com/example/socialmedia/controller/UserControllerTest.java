package com.example.socialmedia.controller;

import com.example.socialmedia.dto.UserDTO;
import com.example.socialmedia.model.User;
import com.example.socialmedia.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createUser_ShouldReturnUser() {
        UserDTO dto = new UserDTO();
        dto.setUsername("user1");

        User savedUser = User.builder().id(1L).username("user1").build();
        when(userService.saveUser(any(User.class))).thenReturn(savedUser);

        User result = userController.createUser(dto);

        assertNotNull(result);
        assertEquals("user1", result.getUsername());
        verify(userService, times(1)).saveUser(any(User.class));
    }

    @Test
    void followUser_ShouldCallService() {
        doNothing().when(userService).followUser(1L, 2L);

        userController.followUser(1L, 2L);

        verify(userService, times(1)).followUser(1L, 2L);
    }
}
