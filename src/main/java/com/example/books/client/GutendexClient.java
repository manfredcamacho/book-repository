package com.example.books.client;

import com.example.books.model.BookResponse;
import com.example.books.model.GutendexResponse;
import com.example.books.service.ApiConnetor;
import com.example.books.service.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GutendexClient {
    @Autowired
    private ApiConnetor apiConnetor;

    private final Mapper mapper = new Mapper();

    public Optional<BookResponse> getBook(String title){
        try {
            String PATH = "?search=";
            String URL_BASE = "https://gutendex.com/books/";
            String endpoint = URL_BASE + PATH + title.replace(" ", "+");
            String json = apiConnetor.get(endpoint);

            return Optional.ofNullable(mapper.toObject(json, GutendexResponse.class).results().getFirst());
        } catch (Exception e) {
            return Optional.empty();
        }
    }
}
