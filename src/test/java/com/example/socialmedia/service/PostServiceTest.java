package com.example.socialmedia.service;

import com.example.socialmedia.dto.PostDTO;
import com.example.socialmedia.dto.PostResponseDTO;
import com.example.socialmedia.model.Post;
import com.example.socialmedia.model.User;
import com.example.socialmedia.repository.PostRepository;
import com.example.socialmedia.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PostServiceTest {

    @Mock
    private PostRepository postRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private PostService postService;

    private User user;
    private Post post;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        user = User.builder().id(1L).username("user1").build();
        post = Post.builder().id(1L).title("Test").body("Body").author(user).likedBy(new HashSet<>()).build();
    }

    @Test
    void createPost_ShouldReturnPostResponseDTO() {
        PostDTO dto = new PostDTO();
        dto.setTitle("Test");
        dto.setBody("Body");
        dto.setAuthorId(1L);

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(postRepository.save(any(Post.class))).thenReturn(post);

        PostResponseDTO response = postService.createPost(dto);

        assertEquals("Test", response.getTitle());
        assertEquals("Body", response.getBody());
        assertEquals("user1", response.getAuthorUsername());
        assertEquals(0, response.getLikes());
    }

    @Test
    void getAllPosts_ShouldReturnList() {
        when(postRepository.findAll()).thenReturn(List.of(post));

        List<PostResponseDTO> result = postService.getAllPosts();

        assertEquals(1, result.size());
        assertEquals("Test", result.get(0).getTitle());
    }

    @Test
    void likePost_ShouldAddUserToLikedBy() {
        when(postRepository.findById(1L)).thenReturn(Optional.of(post));
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        postService.likePost(1L, 1L);

        assertTrue(post.getLikedBy().contains(user));
        verify(postRepository, times(1)).save(post);
    }
}
