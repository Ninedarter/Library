package com.example.Library.config;


import com.example.Library.entity.Author;
import com.example.Library.entity.Book;
import com.example.Library.repository.AuthorRepository;
import com.example.Library.repository.BookRepository;
import com.example.Library.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
@RequiredArgsConstructor
public class ApplicationConfig {

    private final BookService bookService;
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    @Bean
    CommandLineRunner commandLineRunner(BookRepository bookRepository) {
        return args -> {
            Author author1 = new Author("John", "Doe", null);
            authorRepository.save(author1);
            Author author2 = new Author("Mike", "James", null);
            authorRepository.save(author2);

            List<Book> bookList = new ArrayList<>();

            Book book1 = new Book("Book 1", 2001, author1, 2, 3.75);
            Book book2 = new Book("Book 2", 2002, author1, 3, 4.16);
            Book book3 = new Book("Book 3", 2003, author2, 2, 2);
            Book book4 = new Book("Book 4", 2004, author2, 2, 1);
            Book book5 = new Book("Book 5", 2005, author1, 4, 3.5);
            Book book6 = new Book("Book 6", 2006, author2, 3, 3.66);
            bookRepository.saveAll(List.of(book1, book2, book3, book4, book5, book6));


        };

    }

}