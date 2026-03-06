package com.example.w2.tdd.utility;

import com.example.w2.tdd.model.Posts;
import com.example.w2.tdd.repository.IPostRespository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import tools.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;

@Component
class PostDataLoader implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(PostDataLoader.class);
    private final ObjectMapper objectMapper;
    private final IPostRespository postRepository;

    public PostDataLoader(ObjectMapper objectMapper, IPostRespository postRepository) {
        this.objectMapper = objectMapper;
        this.postRepository = postRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (postRepository.count() == 0) {
            String POSTS_JSON = "/static/posts.json";
            log.info("Loading posts into database from JSON: {}", POSTS_JSON);
            try (InputStream inputStream = getClass().getResourceAsStream(POSTS_JSON)) {
                if (inputStream == null) {
                    throw new IOException("Resource not found: " + POSTS_JSON);
                }
                Posts response = objectMapper.readValue(inputStream, Posts.class);
                postRepository.saveAll(response.postList());
            } catch (IOException e) {
                throw new RuntimeException("Failed to read JSON data", e);
            }
        }
    }
}
