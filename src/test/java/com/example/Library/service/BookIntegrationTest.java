package com.example.Library.service;

import com.example.Library.entity.Author;
import com.example.Library.entity.Book;
import com.example.Library.repository.BookRepository;
import com.example.Library.request.Request;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@RequiredArgsConstructor
public class BookIntegrationTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private BookRepository bookRepository;


    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testGetBooksByYear() throws Exception {
        int yearToSearch = 2002;
        mockMvc.perform(get("/api/v1/books/year2002")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Ok"))
                .andExpect(jsonPath("$.books").isArray())
                .andExpect(jsonPath("$.books.length()").value(1)) // Check if the correct number of books is returned
                .andExpect(jsonPath("$.books[0].title").value("Book 2"))
                .andExpect(jsonPath("$.books[0].publicationYear").value(yearToSearch));
    }
}
