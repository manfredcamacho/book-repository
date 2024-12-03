package com.example.books.service;

import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Service
public class ApiConnetor {

    public String get(String uri) throws Exception {
        HttpResponse<String> response;
        try (HttpClient client = HttpClient.newHttpClient()) {
            HttpRequest request = HttpRequest.newBuilder().uri(URI.create(uri)).GET().build();
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        }

        return response.body();

    }
}
