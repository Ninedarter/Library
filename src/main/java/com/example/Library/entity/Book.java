package com.example.Library.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data

public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private int publicationYear;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author;
    private int rating;

    public Book(String title, int publicationYear, Author author, int rating) {
        this.title = title;
        this.publicationYear = publicationYear;
        this.author = author;
        this.rating = rating;
    }
}
