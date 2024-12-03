package com.example.books.repository;

import com.example.books.entity.BookEntity;
import com.example.books.model.Language;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BooksRepository extends JpaRepository<BookEntity, Long> {

    BookEntity findByTitle(String title);

    @Override
    List<BookEntity> findAll();

    @Query(value =  "SELECT b FROM BookEntity b JOIN FETCH b.languages l WHERE l = :lang")
    List<BookEntity> findAllByLanguages(Language lang);
}
