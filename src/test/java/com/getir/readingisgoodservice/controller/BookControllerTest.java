package com.getir.readingisgoodservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.getir.readingisgoodservice.TestInitializer;
import com.getir.readingisgoodservice.model.ApiResponse;
import com.getir.readingisgoodservice.model.request.BookRequest;
import com.getir.readingisgoodservice.model.response.BookResponse;
import com.getir.readingisgoodservice.repository.BookRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;

import static java.math.BigDecimal.TEN;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@ContextConfiguration(initializers = TestInitializer.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
public class BookControllerTest {

    @Autowired
    TestRestTemplate restTemplate;

    @SpyBean
    BookRepository bookRepository;

    @Autowired
    JdbcTemplate jdbcTemplate;

    ObjectMapper objectMapper;

    @Value("${app.security.user.name}")
    private String userName;

    @Value("${app.security.user.password}")
    private String password;

    @BeforeEach
    void init() {
        objectMapper = new ObjectMapper();
        objectMapper.findAndRegisterModules();
    }

    @AfterEach
    void tearDown() {

    }

    @Test
    public void createBookShouldReturnBookResponseWhenRequestIsValid() {
        // Arrange
        BookRequest bookRequest = BookRequest.builder()
                .name("book-name")
                .writer("book-writer")
                .stock(1L)
                .price(TEN)
                .build();

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(HttpHeaders.CONTENT_TYPE, APPLICATION_JSON_VALUE);
        httpHeaders.add(HttpHeaders.ACCEPT, APPLICATION_JSON_VALUE);

        HttpEntity<Object> request = new HttpEntity<>(bookRequest, httpHeaders);

        // Act
        ResponseEntity<ApiResponse> responseEntity = restTemplate
                .withBasicAuth(userName, password)
                .postForEntity("/api/book", request, ApiResponse.class);

        BookResponse bookResponse = objectMapper.convertValue(responseEntity.getBody().getData(), BookResponse.class);

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("book-name", bookResponse.getName());
        assertEquals(TEN, bookResponse.getPrice());
    }
}