package com.example.Library.repository;

import com.example.Library.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findByPublicationYear(int publicationYear);

    List<Book> findByTitleContains(String title);

    Book findByTitle(String title);

    List<Book> findByAuthor_NameAndAuthor_Surname(String name, String surname);

    List<Book> findByAverageRatingBetween(double averageRatingStart, double averageRatingEnd);
}
