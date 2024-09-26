package com.example.Library.response;

import com.example.Library.entity.Book;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class RateBookResponse {

    private String message;
    private Book book;
}
