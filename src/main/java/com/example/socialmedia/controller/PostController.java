package com.example.socialmedia.controller;

import com.example.socialmedia.dto.PostDTO;
import com.example.socialmedia.dto.PostResponseDTO;
import com.example.socialmedia.service.PostService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping
    public PostResponseDTO createPost(@RequestBody PostDTO dto) {
        return postService.createPost(dto);
    }

    @GetMapping
    public List<PostResponseDTO> getAllPosts() {
        return postService.getAllPosts();
    }

    @PostMapping("/{postId}/like/{userId}")
    public void likePost(@PathVariable Long postId, @PathVariable Long userId) {
        postService.likePost(postId, userId);
    }
}
