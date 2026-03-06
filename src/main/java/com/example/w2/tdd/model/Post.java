package com.example.w2.tdd.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.relational.core.mapping.Column;

public record Post(
        @Id
        @Column("id")
        Long id,
        @Column("user_id")
        Integer userId,
        @Column("title")
        String title,
        @Column("body")
        String body,
        @Version
        @Column("version")
        Integer version) {
}