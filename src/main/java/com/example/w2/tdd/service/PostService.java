package com.example.w2.tdd.service;

import com.example.w2.tdd.model.Post;
import com.example.w2.tdd.utility.ServiceManager;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

@Service
public class PostService extends ServiceManager<Post, Long> {


    public PostService(CrudRepository<Post, Long> repository) {
        super(repository);
    }

}
