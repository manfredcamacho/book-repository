package com.example.books.entity;

import com.example.books.model.BookResponse;
import com.example.books.model.Language;
import jakarta.persistence.*;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name = "Book")
public class BookEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false, unique = true)
    private String title;

    @ElementCollection(targetClass = Language.class)
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "book_language", joinColumns = @JoinColumn(name = "book_id"))
    @Column(name = "language", nullable = false)
    private Set<Language> languages;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<AuthorEntity> authors;

    public BookEntity(BookResponse bookResponse) {
        this.title = bookResponse.title();
        this.authors = bookResponse.authors().stream().map(AuthorEntity::new).toList();
        this.languages = bookResponse.languages().stream().map(Language::fromString).collect(Collectors.toSet());
    }

    public BookEntity() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Set<Language> getLanguages() {
        return languages;
    }

    public void setLanguages(Set<Language> languages) {
        this.languages = languages;
    }

    public List<AuthorEntity> getAuthors() {
        return authors;
    }

    public void setAuthors(List<AuthorEntity> authors) {
        this.authors = authors;
    }

    @Override
    public String toString() {
        return "Title: " + title;
    }
}
