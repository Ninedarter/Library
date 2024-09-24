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
            Author author3 = new Author("Chris", "Bosh", null);
            authorRepository.save(author3);
            Author author4 = new Author("Luka", "Doncic", null);
            authorRepository.save(author4);

            List<Book> bookList = new ArrayList<>();

            bookList.add(new Book("Book 1", 2001, author1, 5));
            bookList.add(new Book("Book 2", 2002, author1, 4));
            bookList.add(new Book("Book 3", 2003, author1, 3));
            bookList.add(new Book("Book 4", 2004, author1, 5));
            bookList.add(new Book("Book 5", 2005, author1, 4));
            bookList.add(new Book("Book 6", 2006, author2, 2));
            bookList.add(new Book("Book 7", 2007, author2, 3));
            bookList.add(new Book("Book 8", 2008, author2, 5));
            bookList.add(new Book("Book 9", 2009, author2, 4));
            bookList.add(new Book("Book 10", 2010, author2, 3));
            bookList.add(new Book("Book 11", 2011, author3, 5));
            bookList.add(new Book("Book 12", 2012, author3, 4));
            bookList.add(new Book("Book 13", 2013, author3, 5));
            bookList.add(new Book("Book 14", 2014, author3, 3));
            bookList.add(new Book("Book 15", 2015, author3, 4));
            bookList.add(new Book("Book 16", 2016, author1, 2));
            bookList.add(new Book("Book 17", 2017, author2, 5));
            bookList.add(new Book("Book 18", 2018, author3, 3));
            bookList.add(new Book("Book 19", 2019, author1, 4));
            bookList.add(new Book("Book 20", 2020, author2, 4));
            bookRepository.saveAll(bookList);
        };
    }
}