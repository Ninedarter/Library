package com.example.Library.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String names;
    private String surnames;

    @OneToMany(mappedBy = "author")
    private List<Book> books;

    public Author(String names, String surnames, List<Book> books) {
        this.names = names;
        this.surnames = surnames;
        this.books = books;
    }

}
