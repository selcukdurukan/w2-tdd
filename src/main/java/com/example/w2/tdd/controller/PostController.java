package com.example.w2.tdd.controller;

import com.example.w2.tdd.exception.ResourceNotFoundException;
import com.example.w2.tdd.model.Post;
import com.example.w2.tdd.service.PostService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/api/v1/posts")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("")
    public ResponseEntity<List<Post>> getPosts() {
        return ResponseEntity.ok(postService.findAll());
    }
    @GetMapping("/{id}")
    public ResponseEntity<Post> getPosts(@PathVariable Long id) {
        return ResponseEntity.ok(postService.findAllById(id).orElseThrow(() ->  new ResourceNotFoundException("Post", "Post detail", String.valueOf(id))));
    }
}
