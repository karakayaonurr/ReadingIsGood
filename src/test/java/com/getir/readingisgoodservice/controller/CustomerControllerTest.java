package com.getir.readingisgoodservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.getir.readingisgoodservice.TestInitializer;
import com.getir.readingisgoodservice.model.ApiErrorResponse;
import com.getir.readingisgoodservice.model.ApiResponse;
import com.getir.readingisgoodservice.model.request.CustomerRequest;
import com.getir.readingisgoodservice.model.response.CustomerResponse;
import com.getir.readingisgoodservice.repository.CustomerRepository;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@ContextConfiguration(initializers = TestInitializer.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
public class CustomerControllerTest {

    @Autowired
    TestRestTemplate restTemplate;

    @SpyBean
    CustomerRepository customerRepository;

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
        clearDB();
    }

    @Test
    public void createCustomerShouldReturnCustomerResponseWhenRequestIsValid() {
        // Arrange
        CustomerRequest customerRequest = CustomerRequest.builder()
                .name("customer-name")
                .surname("customer-surname")
                .email("customer@email.com")
                .build();

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(HttpHeaders.CONTENT_TYPE, APPLICATION_JSON_VALUE);
        httpHeaders.add(HttpHeaders.ACCEPT, APPLICATION_JSON_VALUE);

        HttpEntity<Object> request = new HttpEntity<>(customerRequest, httpHeaders);

        // Act
        ResponseEntity<ApiResponse> responseEntity = restTemplate
                .withBasicAuth(userName, password)
                .postForEntity("/api/customer", request, ApiResponse.class);

        CustomerResponse customerResponse = objectMapper.convertValue(responseEntity.getBody().getData(), CustomerResponse.class);

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("customer-name", customerResponse.getName());
        assertEquals("customer@email.com", customerResponse.getEmail());
    }

    @Test
    public void createCustomerShouldReturnCustomerAlreadyExistExceptionWhenCustomerExist() {
        // Generate Data
        generateDB();

        // Arrange
        CustomerRequest customerRequest = CustomerRequest.builder()
                .name("customer-name1")
                .surname("customer-surname1")
                .email("customer1@email.com")
                .build();

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(HttpHeaders.CONTENT_TYPE, APPLICATION_JSON_VALUE);
        httpHeaders.add(HttpHeaders.ACCEPT, APPLICATION_JSON_VALUE);

        HttpEntity<Object> request = new HttpEntity<>(customerRequest, httpHeaders);

        // Act
        ResponseEntity<ApiErrorResponse> responseEntity = restTemplate
                .withBasicAuth(userName, password)
                .postForEntity("/api/customer", request, ApiErrorResponse.class);

        ApiErrorResponse apiErrorResponse = objectMapper.convertValue(responseEntity.getBody(), ApiErrorResponse.class);

        // Assert
        assertEquals(HttpStatus.NOT_ACCEPTABLE, responseEntity.getStatusCode());
        assertEquals(String.valueOf(1003), apiErrorResponse.getErrorCode());
        assertEquals("Customer Already Exists Exception", apiErrorResponse.getErrorMessage());
    }

    private void generateDB() {
        String customerQuery =  "INSERT INTO customer " +
                "(id, created_date, updated_date, version, email, name, surname) " +
                "VALUES (1, now(), NULL, 0, 'customer1@email.com', 'customer-name1', 'customer-surname1'); " +
                "INSERT INTO customer " +
                "(id, created_date, updated_date, version, email, name, surname) " +
                "VALUES (2, now(), NULL, 0, 'customer2@email.com', 'customer-name2', 'customer-surname2'); " +
                "INSERT INTO customer " +
                "(id, created_date, updated_date, version, email, name, surname) " +
                "VALUES (3, now(), NULL, 0, 'customer3@email.com', 'customer-name3', 'customer-surname3'); ";

        jdbcTemplate.execute(customerQuery);
    }

    private void clearDB() {
        String deleteCustomerQuery = "DELETE FROM customer";

        jdbcTemplate.execute(deleteCustomerQuery);
    }
}