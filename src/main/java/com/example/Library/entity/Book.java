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
    private int totalRatings;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author;
    private double averageRating;


    public Book(String title, int publicationYear, Author author) {
        this.title = title;
        this.publicationYear = publicationYear;
        this.author = author;
    }

    public Book(String title, int publicationYear, Author author, int totalRatings , double averageRating) {
        this.title = title;
        this.author = author;
        this.publicationYear = publicationYear;
        this.totalRatings = totalRatings;
        this.averageRating = averageRating;
    }
}
