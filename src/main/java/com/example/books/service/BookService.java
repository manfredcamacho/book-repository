package com.example.books.service;

import com.example.books.client.GutendexClient;
import com.example.books.entity.AuthorEntity;
import com.example.books.entity.BookEntity;
import com.example.books.model.AuthorResponse;
import com.example.books.model.BookResponse;
import com.example.books.model.Language;
import com.example.books.repository.AuthorsRepository;
import com.example.books.repository.BooksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookService {

    @Autowired
    private GutendexClient gutendexClient;
    @Autowired
    private BooksRepository booksRepository;
    @Autowired
    private AuthorsRepository authorsRepository;

    public Optional<BookEntity> findAndSaveBookByTitle(String title) {
       Optional<BookResponse> response = gutendexClient.getBook(title);
       if (response.isPresent()) {
            BookEntity book = toBook(response.get());

            if (booksRepository.findByTitle(book.getTitle()) == null) {
                List<AuthorEntity> persistentAuthors = saveAuthorsIfNotExist(book.getAuthors());
                book.setAuthors(persistentAuthors);
                booksRepository.save(book);
            }
            else{
                System.out.println("The book already exists in the database");
            }

            return Optional.of(book);
       }
       return Optional.empty();
    }

    private List<AuthorEntity> saveAuthorsIfNotExist(List<AuthorEntity> authors) {
        return authors.stream()
                .map(author -> {
                    Optional<AuthorEntity> existingAuthor = authorsRepository.findByName(author.getName());
                    return existingAuthor.orElseGet(() -> authorsRepository.save(author));
                })
                .collect(Collectors.toList());
    }

    public List<BookEntity> getAllBooks() {
        return booksRepository.findAll();
    }

    public List<AuthorEntity> getAllAuthors() {
        return authorsRepository.findAll();
    }

    public List<AuthorEntity> getAuthorsAliveInYear(int year) {
        return authorsRepository.findAllAuthorsAlive(year);
    }

    public List<BookEntity> getBooksByLanguage(Language language) {
        return booksRepository.findAllByLanguages(language);
    }

    private BookEntity toBook(BookResponse bookResponse) {
        return new BookEntity(bookResponse);
    }

    private List<AuthorEntity> toAuthorList(List<AuthorResponse> authorsResponse) {
        return authorsResponse.stream().map(AuthorEntity::new).toList();
    }
}
