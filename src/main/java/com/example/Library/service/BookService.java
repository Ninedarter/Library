package com.example.Library.service;

import com.example.Library.entity.Book;
import com.example.Library.repository.BookRepository;
import com.example.Library.request.Request;
import com.example.Library.response.RateBookResponse;
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

    /**
     * Returns a list of books published in the specific year
     * * @param  year  specific year to find books
     *
     * @return ResponseEntity containing a Response object
     */

    public ResponseEntity<Response> getByYear(final int year) {
        if (year <= 0 || year > LocalDateTime.now().getYear()) {
            return new ResponseEntity<>(new Response("Bad input", (List<Book>) null), HttpStatus.BAD_REQUEST);
        }
        List<Book> booksByYear = bookRepository.findByPublicationYear(year);
        return new ResponseEntity<>(new Response("Ok", booksByYear), HttpStatus.OK);
    }

    /**
     * Returns a list of books with specific title
     * * @param  title  specific title to find books
     *
     * @return ResponseEntity containing a Response object
     */
    public ResponseEntity<Response> getByTitle(String title) {
        if (title == null) {
            return new ResponseEntity<>(new Response("Input cannot be null", (List<Book>) null), HttpStatus.BAD_REQUEST);
        }
        title = title.trim();
        List<Book> booksByYear = bookRepository.findByTitleContains(title);
        return new ResponseEntity<>(new Response("Ok", booksByYear), HttpStatus.OK);
    }

    /**
     * Returns a list all books from given author
     * * @param  request  where Author full name is given
     *
     * @return ResponseEntity containing a Response object
     */

    public ResponseEntity<Response> getByAuthor(Request request) {
        if (request.getAuthorFullName() == null) {
            return new ResponseEntity<>(new Response("Input cannot be null", (List<Book>) null), HttpStatus.BAD_REQUEST);
        }
        String[] fullNameSeparated = request.getAuthorFullName().split(" ");
        List<Book> booksByAuthor = bookRepository.findByAuthor_NameAndAuthor_Surname(fullNameSeparated[0], fullNameSeparated[1]);
        if (booksByAuthor == null) {
            return new ResponseEntity<>(new Response("No data found by this author", (List<Book>) null), HttpStatus.OK);
        }
        return new ResponseEntity<>(new Response("Ok", booksByAuthor), HttpStatus.OK);
    }

    /**
     * Returns a list all books from given range in rating
     * * @param  request the min and max range is given
     *
     * @return ResponseEntity containing a Response object
     */

    public ResponseEntity<Response> getInRange(Request request) {
        if (request.getMinRating() < 1 || request.getMinRating() > 5 || request.getMaxRating() < request.getMinRating() || request.getMaxRating() > 5) {
            return new ResponseEntity<>(new Response("Wrongly chosen ranges", (List<Book>) null), HttpStatus.BAD_REQUEST);
        }
        List<Book> booksByYear = bookRepository.findByAverageRatingBetween(request.getMinRating(), request.getMaxRating());
        return new ResponseEntity<>(new Response("Ok", booksByYear), HttpStatus.OK);
    }

    /**
     * Returns an updated Book object with new average
     * * @param  request where Book to rate and rating is given
     *
     * @return ResponseEntity containing a Response object
     */

    public ResponseEntity<RateBookResponse> rateBook(Request request) {
        if (!rateValueInRange(request)) {
            return new ResponseEntity<>(new RateBookResponse("Rating should be between 1 and 5", null), HttpStatus.BAD_REQUEST);
        }
        if (request.getTitleOfBookToRate() == null) {
            return new ResponseEntity<>(new RateBookResponse("Title cannot be null", null), HttpStatus.BAD_REQUEST);
        }
        Book bookToRate = findByTitle(request.getTitleOfBookToRate());
        int totalNumberOfRatings = bookToRate.getTotalRatings();
        bookToRate.setAverageRating(calculateNewAverage(bookToRate, request.getRatingValue()));
        bookToRate.setTotalRatings(totalNumberOfRatings + 1);
        bookRepository.save(bookToRate);
        return new ResponseEntity<>(new RateBookResponse("Ok", bookToRate), HttpStatus.OK);
    }

    private static boolean rateValueInRange(Request request) {
        return (request.getRatingValue() >= 1 && request.getRatingValue() <= 5);
    }

    private Book findByTitle(String title) {
        return bookRepository.findByTitle(title);
    }

    private double calculateNewAverage(Book book, double rating) {
        double currentAverage = book.getAverageRating();
        int totalNumberOfRatings = book.getTotalRatings();
        return Math.round(((currentAverage * totalNumberOfRatings) + rating) / (totalNumberOfRatings + 1.0) * 100.0) / 100.0;
    }


}



