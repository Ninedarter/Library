package com.example.Library.request;

import lombok.Getter;

@Getter
public class Request {

    private Long id;
    private String title;
    private int publicationYear;
    private Long authorId;
    private int rating;
}