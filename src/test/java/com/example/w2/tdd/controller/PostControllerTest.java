package com.example.w2.tdd.controller;

import com.example.w2.tdd.exception.ResourceNotFoundException;
import com.example.w2.tdd.model.Post;
import com.example.w2.tdd.service.PostService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@WebMvcTest(PostController.class)
@AutoConfigureMockMvc
class PostControllerTest {

    @Autowired
    MockMvc mockMvc;
    List<Post> posts = new ArrayList<>();

    @MockitoBean
    PostService postService;

    @BeforeEach
    void setUp() {
        posts.add(new Post(1L, 1, "Hello", "First post", null));
        posts.add(new Post(2L, 1, "Second post", "Secpnd post", null));
    }

    @Test
    void shouldFindAllPosts() throws Exception {
        //arrange,
        Mockito.when(postService.findAll()).thenReturn(posts);
        String JSON = """
                [
                    {
                        "id": 1,
                        "userId": 1,
                        "title": "Hello",
                        "body": "First post",
                        "version": null
                    },
                    {
                        "id": 2,
                        "userId": 1,
                        "title": "Second post",
                        "body": "Secpnd post",
                        "version": null
                    }
                ]
                """;
        // act, result

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/posts"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().json(JSON));
    }

    @Test
    void shouldFindPostWhenGivenId() throws Exception {
        //arrange
        Mockito.when(postService.findAllById(1L)).thenReturn(Optional.of(posts.get(0)));
        String JSON = """
                    {
                        "id": 1,
                        "userId": 1,
                        "title": "Hello",
                        "body": "First post",
                        "version": null
                    }
                """;
        //act, result
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/posts/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().json(JSON));
    }


    @Test
    void shouldReturnError_WhenGivenInputCantBeFound() throws Exception {
        //Arrange
        long givenId = 3L;
        Mockito.when(postService.findAllById(givenId)).thenThrow(new ResourceNotFoundException("Post", "Post detail", String.valueOf(givenId)));

        //act, result
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/posts/" + givenId))
                .andExpect(MockMvcResultMatchers.status().is4xxClientError())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"));
    }
}