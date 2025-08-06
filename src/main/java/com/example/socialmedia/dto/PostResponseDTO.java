package com.example.socialmedia.dto;

import lombok.Data;

@Data
public class PostResponseDTO {
    private Long id;
    private String title;
    private String body;
    private String authorUsername;
    private int likes;
}
