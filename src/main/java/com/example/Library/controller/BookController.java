package com.example.Library.controller;

import com.example.Library.request.Request;
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

    @PostMapping("/byTitle")
    public ResponseEntity<Response> getByTitle(@RequestBody Request request) {
 String vienas ;
        ResponseEntity<Response> booksByTitle = bookService.getByTitle(request.getFilter());
        return booksByTitle;
    }

}
