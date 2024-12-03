package com.example.books.model;

public enum Language {
    EN,
    ES,
    FR,
    DE,
    TL;

    public static Language fromString(String code) {
        try {
            return Language.valueOf(code.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid language: " + code);
        }
    }
}
