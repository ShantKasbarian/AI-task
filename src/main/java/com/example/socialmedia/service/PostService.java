package com.example.socialmedia.service;

import com.example.socialmedia.dto.PostDTO;
import com.example.socialmedia.dto.PostResponseDTO;
import com.example.socialmedia.model.Post;
import com.example.socialmedia.model.User;
import com.example.socialmedia.repository.PostRepository;
import com.example.socialmedia.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public PostService(PostRepository postRepository, UserRepository userRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    public PostResponseDTO createPost(PostDTO dto) {
        User author = userRepository.findById(dto.getAuthorId()).orElseThrow();
        Post post = Post.builder()
                .title(dto.getTitle())
                .body(dto.getBody())
                .author(author)
                .build();
        postRepository.save(post);
        return mapToDTO(post);
    }

    public List<PostResponseDTO> getAllPosts() {
        return postRepository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public void likePost(Long postId, Long userId) {
        Post post = postRepository.findById(postId).orElseThrow();
        User user = userRepository.findById(userId).orElseThrow();
        post.getLikedBy().add(user);
        postRepository.save(post);
    }

    private PostResponseDTO mapToDTO(Post post) {
        PostResponseDTO dto = new PostResponseDTO();
        dto.setId(post.getId());
        dto.setTitle(post.getTitle());
        dto.setBody(post.getBody());
        dto.setAuthorUsername(post.getAuthor().getUsername());
        dto.setLikes(post.getLikedBy().size());
        return dto;
    }
}
