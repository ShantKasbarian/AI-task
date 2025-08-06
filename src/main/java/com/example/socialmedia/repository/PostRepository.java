package com.example.socialmedia.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.socialmedia.model.Post;

public interface PostRepository extends JpaRepository<Post, Long> {
}
