package com.example.books.entity;

import com.example.books.model.AuthorResponse;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "Author")
public class AuthorEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true)
    private String name;
    private int birthYear;
    private int deathYear;
    @ManyToMany(mappedBy = "authors")
    private List<BookEntity> books;
    
    public AuthorEntity(AuthorResponse authorResponse) {
        this.name = authorResponse.name();
        this.birthYear = authorResponse.birthYear();
        this.deathYear = authorResponse.deathYear();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBirthYear() {
        return birthYear;
    }

    public void setBirthYear(int birthYear) {
        this.birthYear = birthYear;
    }

    public int getDeathYear() {
        return deathYear;
    }

    public void setDeathYear(int deathYear) {
        this.deathYear = deathYear;
    }

    public AuthorEntity() {}

    @Override
    public String toString() {
        return name + " (" + birthYear + "-" + deathYear + ")" ;
    }
}
