package com.example.socialmedia.dto;

import lombok.Data;

@Data
public class PostDTO {
    private String title;
    private String body;
    private Long authorId;
}
