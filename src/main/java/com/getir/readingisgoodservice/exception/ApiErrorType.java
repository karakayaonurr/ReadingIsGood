package com.getir.readingisgoodservice.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ApiErrorType {
    INTERNAL_SERVER_ERROR(1000, "Internal Server Exception", HttpStatus.INTERNAL_SERVER_ERROR),
    FIELD_VALIDATION_ERROR(1001, "Field Validation Exception", HttpStatus.BAD_REQUEST),
    CUSTOMER_NOT_FOUND_ERROR(1002, "Customer Not Found Exception", HttpStatus.NOT_FOUND),
    AUTHENTICATION_ERROR(1003, "Authentication Exception", HttpStatus.UNAUTHORIZED),
    CUSTOMER_EXISTS_ERROR(1004, "Customer Already Exists Exception", HttpStatus.NOT_ACCEPTABLE);

    private final int errorCode;
    private final String errorMessage;
    private final HttpStatus httpStatus;
}
