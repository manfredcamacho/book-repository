package com.example.books.repository;

import com.example.books.entity.AuthorEntity;
import com.example.books.entity.BookEntity;
import com.example.books.model.Language;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface AuthorsRepository extends JpaRepository<AuthorEntity, Long> {

    Optional<AuthorEntity> findByName(String name);

    List<AuthorEntity> findAll();

    @Query(value = "SELECT a FROM AuthorEntity a WHERE a.birthYear <= :year AND a.deathYear >= :year")
    List<AuthorEntity> findAllAuthorsAlive(int year);

}
