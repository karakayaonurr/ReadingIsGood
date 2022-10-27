package com.getir.readingisgoodservice.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_ACCEPTABLE;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;

@Getter
@AllArgsConstructor
public enum ApiErrorType {
    INTERNAL_SERVER_EXCEPTION(1000, "Internal Server Exception", INTERNAL_SERVER_ERROR),
    AUTHENTICATION_EXCEPTION(1001, "Authentication Exception", UNAUTHORIZED),
    FIELD_VALIDATION_EXCEPTION(1002, "Field Validation Exception", BAD_REQUEST),
    CUSTOMER_EXISTS_EXCEPTION(1003, "Customer Already Exists Exception", NOT_ACCEPTABLE),
    CUSTOMER_NOT_FOUND_EXCEPTION(1004, "Customer Not Found Exception", NOT_FOUND),
    BOOK_EXIST_EXCEPTION(1005, "Book Already Exists Exception", NOT_ACCEPTABLE),
    BOOK_NOT_FOUND_EXCEPTION(1006, "Book Not Found Exception", NOT_FOUND),
    ORDER_NOT_FOUND_EXCEPTION(1007, "Order Not Found Exception", NOT_FOUND),
    STATISTICS_NOT_FOUND_EXCEPTION(1008, "Statistics Not Found Exception", NOT_FOUND);

    private final int errorCode;
    private final String errorMessage;
    private final HttpStatus httpStatus;
}
