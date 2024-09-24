package com.example.Library.service;

import com.example.Library.entity.Book;
import com.example.Library.repository.BookRepository;
import com.example.Library.response.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

//    public ResponseEntity<Response> getByAuthor(String filter) {
//        if (filter == null) {
//            return new ResponseEntity<Response>(new Response("Not founded", null), HttpStatus.BAD_REQUEST);
//
//        }
//
//
//
//
//    }


    public ResponseEntity<Response> getByYear(final int year) {
        if (year <= 0 || year > LocalDateTime.now().getYear()) {
            return new ResponseEntity<>(new Response("Bad input", null), HttpStatus.BAD_REQUEST);
        }
        List<Book> booksByYear = bookRepository.findByPublicationYear(year);
        return new ResponseEntity<>(new Response("Ok", booksByYear), HttpStatus.OK);
    }

    public ResponseEntity<Response> getByTitle(String title) {
        if (title == null) {
            return new ResponseEntity<Response>(new Response("Not founded", null), HttpStatus.BAD_REQUEST);
        }
        List<Book> booksByYear = bookRepository.findByTitle(title);
        return new ResponseEntity<>(new Response("Ok", booksByYear), HttpStatus.OK);
    }
}



