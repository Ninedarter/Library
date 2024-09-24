package com.example.Library.response;

import com.example.Library.entity.Book;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class Response {

    private String message;
    private List<Book> books;
}
