package com.example.w2.tdd.dto;

import jakarta.validation.constraints.NotEmpty;

public record SavePostRequestDto(
        Integer userId,
        @NotEmpty
        String title,
        String body,
        Integer version
) {

}
