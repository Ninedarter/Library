package com.example.Library.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private int publicationYear;
    private double rating;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author;

    public Book(String title, int publicationYear, Author author, int rating) {
        this.title = title;
        this.publicationYear = publicationYear;
        this.author = author;
        this.rating = rating;
    }
}
