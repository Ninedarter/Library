package com.example.Library.repository;

import com.example.Library.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findByAuthor_NamesAndAuthor_Surnames(String names, String surnames);

    List<Book> findByPublicationYear(int publicationYear);

    List<Book> findByTitleContains(String title);

    List<Book> findByTitle(String title);
}
