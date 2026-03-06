package com.example.w2.tdd.repository;

import com.example.w2.tdd.model.Post;
import org.springframework.data.repository.CrudRepository;

public interface IPostRespository extends CrudRepository<Post, Long> {
}
