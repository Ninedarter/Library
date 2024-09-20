package com.example.Library.controller;

import com.example.Library.entity.Book;
import com.example.Library.request.Request;
import com.example.Library.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/books")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @GetMapping("/all")
    public List<Book> getAll() {
        return bookService.findAll();
    }

    public ResponseEntity<List<Book>> getByYear(Request request) {
        return new ResponseEntity(bookService.getByYear(request), HttpStatus.OK);
    }
}
