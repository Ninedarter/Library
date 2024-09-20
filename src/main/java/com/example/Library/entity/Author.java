package com.example.Library.entity;

import jakarta.persistence.*;

import java.util.Set;

@Entity
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String names;
    private String surnames;

    @OneToMany(mappedBy = "author")
    private Set<Book> books;

}
