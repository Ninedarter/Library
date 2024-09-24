package com.example.Library.controller;

import com.example.Library.Request.Request;
import com.example.Library.response.Response;
import com.example.Library.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/books/")
@Controller
public class BookController {

    private final BookService bookService;

    @GetMapping
    @RequestMapping("byTitle")
    public ResponseEntity<Response> getByTitle(@RequestBody Request request) {

        ResponseEntity<Response> booksByTitle = bookService.getByTitle(request.getFilter());
        return booksByTitle;
    }

}
