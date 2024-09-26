package com.example.Library.service;

import com.example.Library.entity.Author;
import com.example.Library.entity.Book;
import com.example.Library.repository.BookRepository;
import com.example.Library.request.Request;
import com.example.Library.response.RateBookResponse;
import com.example.Library.response.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;

class BookServiceTest {

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookService bookService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this); // Initialize mocks
    }

    //by year
    @Test
    void shouldFindBooksWhenYearsIsGiven() {

        Book book = new Book("Java for dummies", 2022, null);
        Book book2 = new Book("Comedy", 2022, null);
        List<Book> bookList = Arrays.asList(book, book2);
        int yearToFind = 2001;
        when(bookRepository.findByPublicationYear(yearToFind)).thenReturn(bookList);
        List<Book> booksByYear = bookService.getByYear(yearToFind).getBody().getBooks();
        assertEquals(2, booksByYear.size());
    }

    @Test
    void shouldReturnNullWhenYearIsLessThan0() {
        Book book = new Book("Java for dummies", 2022, null);
        Book book2 = new Book("Comedy", 2022, null);
        List<Book> bookList = Arrays.asList(book, book2);
        int yearToFind = -1;
        ResponseEntity<Response> response = bookService.getByYear(yearToFind);
        assertNull(response.getBody().getBooks());
    }

    @Test
    void shouldReturnNullAndBadRequestStatusWhenYearIsMoreThanDateIsAfterToday() {
        Book book = new Book("Java for dummies", 2022, null);
        Book book2 = new Book("Comedy", 2022, null);
        List<Book> bookList = Arrays.asList(book, book2);
        int yearToFind = LocalDateTime.now().getYear() + 1;
        ResponseEntity<Response> response = bookService.getByYear(yearToFind);
        assertNull(Objects.requireNonNull(response.getBody()).getBooks());
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    //bytitle
    @Test
    void shouldFindAllBooksWhenTitleIsContainingGivenSearchTitle() {
        Book book = new Book("Harry Potter", 1997, null);
        Book book2 = new Book("Harry Potter 2", 1998, null);
        List<Book> bookList = Arrays.asList(book, book2);
        when(bookRepository.findByTitleContains("Harry Potter")).thenReturn(bookList);
        ResponseEntity<Response> response = bookService.getByTitle("Harry Potter");
        assertEquals(2, response.getBody().getBooks().size());
    }


    @Test
    void shouldReturnSpecificMessageWhenTitleIsNull() {
        ResponseEntity<Response> response = bookService.getByTitle(null);
        assertEquals("Input cannot be null", response.getBody().getMessage());
    }

    @Test
    void shouldReturnSpecificMessageWhenGivenAuthorIsNull() {
        Request request = new Request(0, 0);
        ResponseEntity<Response> response = bookService.getByAuthor(request);
        assertEquals("Input cannot be null", response.getBody().getMessage());
    }

    @Test
    void shouldFindAllBooksByGivenAuthor() {
        Book book = new Book("Harry Potter", 1997, new Author("J.K", "Rowling", null));
        Book book2 = new Book("Harry Potter 2", 1998, new Author("J.K", "Rowling", null));
        List<Book> bookList = Arrays.asList(book, book2);
        Request request = new Request(null, "J.K Rowling");
        when(bookRepository.findByAuthor_NameAndAuthor_Surname("J.K", "Rowling")).thenReturn(bookList);
        ResponseEntity<Response> response = bookService.getByAuthor(request);
        assertEquals(2, response.getBody().getBooks().size());
        assertEquals(1997, response.getBody().getBooks().get(0).getPublicationYear());
    }

    @Test
    void shouldReturnSpecificMessageIfNoDataFoundByGivenAuthor() {
        Book book = new Book("Harry Potter", 1997, new Author("J.K", "Rowling", null));
        Book book2 = new Book("Harry Potter 2", 1998, new Author("J.K", "Rowling", null));
        Request request = new Request(null,"Anthony Bosh");
        when(bookRepository.findByAuthor_NameAndAuthor_Surname("Anthony", "Bosh")).thenReturn(null);
        ResponseEntity<Response> response = bookService.getByAuthor(request);
        assertEquals("No data found by this author", response.getBody().getMessage());

    }

    @Test
    void shouldReturnSpecificMessageWhenMinRangeIsMinus1() {
        Request request = new Request(-1.0, 5.0);
        ResponseEntity<Response> response = bookService.getInRange(request);
        assertEquals("Wrongly chosen ranges", response.getBody().getMessage());
    }

    @Test
    void shouldReturnSpecificMessageWhenMaxRangeIsOver5() {
        Request request = new Request(1.0, 5.1);
        ResponseEntity<Response> response = bookService.getInRange(request);
        assertEquals("Wrongly chosen ranges", response.getBody().getMessage());
    }

    @Test
    void shouldReturnSpecificMessageWhenMinRangeLargerThanMaxRange() {
        Request request = new Request(2.9, 1.9);
        ResponseEntity<Response> response = bookService.getInRange(request);
        assertEquals("Wrongly chosen ranges", response.getBody().getMessage());
    }

    @Test
    void shouldReturnAllBooksInRangeOfRating() {
        Book book = new Book("Harry Potter", 1997, new Author("J.K", "Rowling", null), 2, 3.9);
        Book book2 = new Book("Harry Potter 2", 1998, new Author("J.K", "Rowling", null), 3, 3.5);
        List<Book> bookList = Arrays.asList(book, book2);
        when(bookRepository.findByAverageRatingBetween(1.0, 4.0)).thenReturn(bookList);
        Request request = new Request(1.0, 4.0);
        ResponseEntity<Response> response = bookService.getInRange(request);
        assertEquals(2, response.getBody().getBooks().size());
        assertEquals("Harry Potter", response.getBody().getBooks().get(0).getTitle());
    }

    @Test
    void shouldChangeAverageAfterRate() {
        Book book = new Book("Harry Potter", 1997, new Author("J.K", "Rowling", null), 2, 3.9);
        when(bookRepository.findByTitle("Harry Potter")).thenReturn(book);
        Request request = new Request("Harry Potter", 5.0);
        ResponseEntity<RateBookResponse> rateBookResponseResponseEntity = bookService.rateBook(request);
        assertEquals(4.27, rateBookResponseResponseEntity.getBody().getBook().getAverageRating());
    }

    @Test
    void shouldReturnSpecificMessageWhenRateValueIsLessThan1() {
        Request request = new Request("Harry Potter", 0.9);
        ResponseEntity<RateBookResponse> rateBookResponseResponseEntity = bookService.rateBook(request);
        assertEquals("Rating should be between 1 and 5", rateBookResponseResponseEntity.getBody().getMessage());
    }

    @Test
    void shouldReturnSpecificMessageWhenBookToRateTitleIsNull() {
        Request request = new Request(null, 1);
        ResponseEntity<RateBookResponse> rateBookResponseResponseEntity = bookService.rateBook(request);
        assertEquals("Title cannot be null", rateBookResponseResponseEntity.getBody().getMessage());
    }

}