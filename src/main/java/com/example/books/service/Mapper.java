package com.example.books.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Mapper {
    private ObjectMapper mapper = new ObjectMapper();

    public <T> T toObject(String json, Class<T> clazz){
        try{
            return mapper.readValue(json, clazz);
        }catch (JsonProcessingException e){
            throw new RuntimeException(e);
        }
    }
}
