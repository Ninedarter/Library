package com.example.Library.service;

import com.example.Library.entity.Author;
import com.example.Library.entity.Book;
import com.example.Library.repository.BookRepository;
import com.example.Library.request.Request;
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

        Book book = new Book(1L, "Java for dummies", 2022, 4, null);
        Book book2 = new Book(2L, "Comedy", 2022, 4.9, null);
        List<Book> bookList = Arrays.asList(book, book2);
        int yearToFind = 2001;
        when(bookRepository.findByPublicationYear(yearToFind)).thenReturn(bookList);
        List<Book> booksByYear = bookService.getByYear(yearToFind).getBody().getBooks();
        assertEquals(2, booksByYear.size());
    }

    @Test
    void shouldReturnNullWhenYearIsLessThan0() {
        Book book = new Book(1L, "Java for dummies", 2022, 4, null);
        Book book2 = new Book(2L, "Comedy", 2022, 4.9, null);
        List<Book> bookList = Arrays.asList(book, book2);
        int yearToFind = -1;
        ResponseEntity<Response> response = bookService.getByYear(yearToFind);
        assertNull(response.getBody().getBooks());
    }

    @Test
    void shouldReturnNullAndBadRequestStatusWhenYearIsMoreThanDateIsAfterToday() {
        Book book = new Book(1L, "Java for dummies", 2022, 4, null);
        Book book2 = new Book(2L, "Comedy", 2022, 4.9, null);
        List<Book> bookList = Arrays.asList(book, book2);
        int yearToFind = LocalDateTime.now().getYear() + 1;
        ResponseEntity<Response> response = bookService.getByYear(yearToFind);
        assertNull(Objects.requireNonNull(response.getBody()).getBooks());
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    //bytitle
    @Test
    void shouldFindAllBooksWhenTitleIsContainingGivenSearchTitle() {
        Book book = new Book(1L, "Harry Potter", 1996, 4, null);
        Book book2 = new Book(2L, "Harry Potter 2", 1998, 4.9, null);
        List<Book> bookList = Arrays.asList(book, book2);
        when(bookRepository.findByTitleContains("Harry Potter")).thenReturn(bookList);
        ResponseEntity<Response> response = bookService.getByTitle("Harry Potter");
        assertEquals(2, response.getBody().getBooks().size());
    }

    //by author
    @Test
    void shouldReturnSpecificMessageWhenTitleIsNull() {
        ResponseEntity<Response> response = bookService.getByTitle(null);
        assertEquals("Input cannot be null", response.getBody().getMessage());
    }

    @Test
    void shouldReturnSpecificMessageWhenGivenAuthorIsNull() {
        Request request = new Request(null, null, 0, 0);
        ResponseEntity<Response> response = bookService.getByAuthor(request);
        assertEquals("Input cannot be null", response.getBody().getMessage());
    }

    @Test
    void shouldFindAllBooksByGivenAuthor() {
        Book book = new Book(1L, "Harry Potter", 1996, 4, new Author("J.K", "Rowling", null));
        Book book2 = new Book(2L, "Harry Potter 2", 1998, 4.9, new Author("J.K", "Rowling", null));
        List<Book> bookList = Arrays.asList(book, book2);
        Request request = new Request(null, "J.K Rowling", 0, 0);
        when(bookRepository.findByAuthor_NameAndAuthor_Surname("J.K", "Rowling")).thenReturn(bookList);
        ResponseEntity<Response> response = bookService.getByAuthor(request);
        assertEquals(2, response.getBody().getBooks().size());
        assertEquals(1996, response.getBody().getBooks().get(0).getPublicationYear());
    }

    @Test
    void shouldReturnSpecificMessageIfNoDataFoundByGivenAuthor() {
        Book book = new Book(1L, "Harry Potter", 1996, 4, new Author("J.K", "Rowling", null));
        Book book2 = new Book(2L, "Harry Potter 2", 1998, 4.9, new Author("J.K", "Rowling", null));
        List<Book> bookList = Arrays.asList(book, book2);
        Request request = new Request(null, "Anthony Bosh", 0, 0);
        when(bookRepository.findByAuthor_NameAndAuthor_Surname("Anthony", "Bosh")).thenReturn(null);
        ResponseEntity<Response> response = bookService.getByAuthor(request);
        assertNull(response.getBody().getBooks());
    }

    @Test
    void shouldReturnSpecificMessageWhenMinRangeIsMinus1() {
        Request request = new Request(null, null, -1.0, 5.0);
        ResponseEntity<Response> response = bookService.getInRange(request);
        assertEquals("Wrongly chosen ranges", response.getBody().getMessage());
    }

    @Test
    void shouldReturnSpecificMessageWhenMaxRangeIsOver5() {
        Request request = new Request(null, null, 1.0, 5.1);
        ResponseEntity<Response> response = bookService.getInRange(request);
        assertEquals("Wrongly chosen ranges", response.getBody().getMessage());
    }

    @Test
    void shouldReturnSpecificMessageWhenMinRangeLargerThanMaxRange() {
        Request request = new Request(null, null, 2.9, 1.9);
        ResponseEntity<Response> response = bookService.getInRange(request);
        assertEquals("Wrongly chosen ranges", response.getBody().getMessage());
    }

    @Test
    void shouldReturnAllBooksInRangeOfRating() {
        Book book = new Book(1L, "Harry Potter", 1996, 4, new Author("J.K", "Rowling", null));
        Book book2 = new Book(2L, "Harry Potter 2", 1998, 4.9, new Author("J.K", "Rowling", null));
        List<Book> bookList = Arrays.asList(book, book2);
        when(bookRepository.findByRatingBetween(1.0, 4.0)).thenReturn(bookList);
        Request request = new Request(null, null, 1.0, 4.0);
        ResponseEntity<Response> response = bookService.getInRange(request);
        assertEquals(2, response.getBody().getBooks().size());
        assertEquals("Harry Potter", response.getBody().getBooks().get(0).getTitle());
    }
}