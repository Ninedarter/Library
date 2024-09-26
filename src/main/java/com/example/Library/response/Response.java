package com.example.Library.response;

import com.example.Library.entity.Book;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Response {

    private String message;
    private List<Book> books;
    private Book updatedBook;

    public Response(String message, List<Book> books) {
        this.message = message;
        this.books = books;
    }

    public Response(String message, Book updatedBook) {
        this.message = message;
        this.updatedBook = updatedBook;
    }
}

