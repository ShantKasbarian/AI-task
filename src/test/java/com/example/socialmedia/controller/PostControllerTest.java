package com.example.socialmedia.controller;

import com.example.socialmedia.dto.PostDTO;
import com.example.socialmedia.dto.PostResponseDTO;
import com.example.socialmedia.service.PostService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PostControllerTest {

    @Mock
    private PostService postService;

    @InjectMocks
    private PostController postController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createPost_ShouldReturnPostResponse() {
        PostDTO dto = new PostDTO();
        dto.setTitle("Test");
        dto.setBody("Body");
        dto.setAuthorId(1L);

        PostResponseDTO response = new PostResponseDTO();
        response.setId(1L);
        response.setTitle("Test");
        response.setBody("Body");
        response.setAuthorUsername("user1");
        response.setLikes(0);

        when(postService.createPost(dto)).thenReturn(response);

        PostResponseDTO result = postController.createPost(dto);

        assertNotNull(result);
        assertEquals("Test", result.getTitle());
        verify(postService, times(1)).createPost(dto);
    }

    @Test
    void getAllPosts_ShouldReturnList() {
        when(postService.getAllPosts()).thenReturn(List.of());

        List<PostResponseDTO> result = postController.getAllPosts();

        assertNotNull(result);
        assertEquals(0, result.size());
        verify(postService, times(1)).getAllPosts();
    }

    @Test
    void likePost_ShouldCallService() {
        doNothing().when(postService).likePost(1L, 1L);

        postController.likePost(1L, 1L);

        verify(postService, times(1)).likePost(1L, 1L);
    }
}
