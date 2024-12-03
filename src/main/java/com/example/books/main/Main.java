package com.example.books.main;

import com.example.books.entity.AuthorEntity;
import com.example.books.entity.BookEntity;
import com.example.books.model.Language;
import com.example.books.service.BookService;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class Main {

    private BookService bookService;

    public Main(BookService bookService) {
        this.bookService = bookService;
    }

    private final Scanner scanner = new Scanner(System.in);

    public void showMenu() {
        int choice;
        do{
            printMenu();
            choice = Integer.parseInt(scanner.nextLine());

            switch (choice){
                case 1:
                    searchBookOnline();
                    break;
                case 2:
                    searchAllStoredBooks();
                    break;
                case 3:
                    searchAllAuthors();
                    break;
                case 4:
                    searchAliveAuthorsByYear();
                    break;
                case 5:
                    searchBooksByLanguage();
                    break;
                case 0:
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Try again.");
                    break;
            }
        } while (choice != 0);
    }

    private void searchBooksByLanguage() {
        int choice = -1;
        do{
            System.out.println("Select language:");
            for (Language lang: Language.values()){
                System.out.println(lang.ordinal() + 1 + ".- " + lang);
            }
            choice =  Integer.parseInt(scanner.nextLine());

        } while (choice <= 0 || choice > Language.values().length);

        Language language = Language.values()[choice - 1];

        List<BookEntity> books = bookService.getBooksByLanguage(language);

        books.forEach(b -> System.out.println(b.getTitle() + " - [" + b.getLanguages()+ "]"));
    }

    private void searchAllAuthors() {
        List<AuthorEntity> authors = bookService.getAllAuthors();
        authors.forEach(System.out::println);
    }

    private void searchAllStoredBooks() {
        List<BookEntity> books = bookService.getAllBooks();
        books.forEach(b -> System.out.println(b.getTitle()));
    }

    private void searchAliveAuthorsByYear() {
        System.out.println("Enter year: ");
        int year = Integer.parseInt(scanner.nextLine());

        List<AuthorEntity> authors = bookService.getAuthorsAliveInYear(year);

        if (!authors.isEmpty()){
            authors.forEach(System.out::println);
        } else {
            System.out.println("No authors found for year " + year);
        }
    }

    private void searchBookOnline() {
        System.out.println("Enter the book title: ");
        String bookTitle = scanner.nextLine();

        Optional<BookEntity> optionalBook = bookService.findAndSaveBookByTitle(bookTitle);
        if (optionalBook.isEmpty()) {
            System.out.println("Book not found.");
        } else {
            BookEntity book = optionalBook.get();
            System.out.println("Title: " + book.getTitle());
            System.out.println("Authors: " + book.getAuthors());
            System.out.println("Languages: " + book.getLanguages());
        }

    }

    private void printMenu() {
        var menu = """
                
                Choose an option:
                1.- Search bookResponse by title
                2.- List all books
                3.- List all authors
                4.- List authors who were alive in specific year
                5.- List books by language
                0.- Exit program
                """;

        System.out.println(menu);
    }
}
