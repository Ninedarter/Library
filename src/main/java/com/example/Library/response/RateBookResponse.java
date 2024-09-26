package com.example.Library.response;

import com.example.Library.entity.Book;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RateBookResponse {

    private String message;
    private Book book;
}
