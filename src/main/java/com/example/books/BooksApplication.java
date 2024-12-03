package com.example.books;

import com.example.books.main.Main;
import com.example.books.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BooksApplication implements CommandLineRunner {

	@Autowired
	private BookService bookService;

	public static void main(String[] args) {
		SpringApplication.run(BooksApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Main main = new Main(bookService);
		main.showMenu();
	}
}
