package com.example.books.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record BookResponse(
        String title,
        List<AuthorResponse> authors,
        List<String> languages
) {
}
