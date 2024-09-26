package com.example.Library.controller;

import com.example.Library.request.Request;
import com.example.Library.response.RateBookResponse;
import com.example.Library.response.Response;
import com.example.Library.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/books")
@Controller
public class BookController {

    private final BookService bookService;

    @PostMapping("/title")
    public ResponseEntity<Response> getByTitle(@RequestBody Request request) {
        ResponseEntity<Response> response = bookService.getByTitle(request.getFilter());
        return response;
    }

    @GetMapping("/year{filter}")
    public ResponseEntity<Response> getByYear(@PathVariable int filter) {
        ResponseEntity<Response> response = bookService.getByYear(filter);
        return response;
    }

    @PostMapping("/author")
    public ResponseEntity<Response> getByAuthor(@RequestBody Request request) {
        ResponseEntity<Response> response = bookService.getByAuthor(request);
        return response;
    }


    @PostMapping("/rating")
    public ResponseEntity<Response> getByRating(@RequestBody Request request) {
        ResponseEntity<Response> response = bookService.getInRange(request);
        return response;
    }

    @PostMapping("/rate")
    public ResponseEntity<RateBookResponse> rateBook(@RequestBody Request request) {
        ResponseEntity<RateBookResponse> response = bookService.rateBook(request);
        return response;
    }
}
