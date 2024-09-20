package com.example.Library.service;

import com.example.Library.entity.Book;
import com.example.Library.repository.BookRepository;
import com.example.Library.request.Request;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    public List<Book> getByYear(Request request) {
        if (request.getPublicationYear() != 0 && request.getPublicationYear() <= LocalDate.now().getYear()) {
            return bookRepository.findByPublicationYear(request.getPublicationYear());
        }
        return null;
    }
}
