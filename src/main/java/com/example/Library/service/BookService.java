package com.example.Library.service;

import com.example.Library.entity.Book;
import com.example.Library.repository.BookRepository;
import com.example.Library.request.Request;
import com.example.Library.response.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    public ResponseEntity<Response> getByYear(final int year) {
        if (year <= 0 || year > LocalDateTime.now().getYear()) {
            return new ResponseEntity<>(new Response("Bad input", null), HttpStatus.BAD_REQUEST);
        }
        List<Book> booksByYear = bookRepository.findByPublicationYear(year);
        return new ResponseEntity<>(new Response("Ok", booksByYear), HttpStatus.OK);
    }

    public ResponseEntity<Response> getByTitle(String title) {
        if (title == null) {
            return new ResponseEntity<Response>(new Response("Input cannot be null", null), HttpStatus.BAD_REQUEST);
        }
        List<Book> booksByYear = bookRepository.findByTitleContains(title);
        return new ResponseEntity<>(new Response("Ok", booksByYear), HttpStatus.OK);
    }

    public ResponseEntity<Response> getByAuthor(Request request) {
        if (request.getAuthorFullName() == null) {
            return new ResponseEntity<Response>(new Response("Input cannot be null", null), HttpStatus.BAD_REQUEST);
        }
        String[] fullNameSeparated = request.getAuthorFullName().split(" ");
        List<Book> byAuthor_nameAndAuthor_surname = bookRepository.findByAuthor_NameAndAuthor_Surname(fullNameSeparated[0], fullNameSeparated[1]);
        if (byAuthor_nameAndAuthor_surname.isEmpty()) {
            return new ResponseEntity<>(new Response("No data found by this author", null), HttpStatus.OK);
        }
        return new ResponseEntity<>(new Response("Ok", byAuthor_nameAndAuthor_surname), HttpStatus.OK);
    }

    public ResponseEntity<Response> getInRange(Request request) {
        if (request.getMinRating() < 0 || request.getMinRating() > 10 || request.getMaxRating() < request.getMinRating() || request.getMaxRating() > 10) {
            return new ResponseEntity<Response>(new Response("Wrongly chosen ranges", null), HttpStatus.OK);
        }
        List<Book> booksByYear = bookRepository.findByRatingBetween(request.getMinRating(), request.getMaxRating());
        return new ResponseEntity<>(new Response("Ok", booksByYear), HttpStatus.OK);
    }

}



