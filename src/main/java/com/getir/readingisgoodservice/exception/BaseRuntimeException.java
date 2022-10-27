package com.getir.readingisgoodservice.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class BaseRuntimeException extends RuntimeException {
    private final int errorCode;
    private final String errorMessage;
    private final HttpStatus httpStatus;

    public BaseRuntimeException(ApiErrorType apiErrorType) {
        this.errorCode = apiErrorType.getErrorCode();
        this.errorMessage = apiErrorType.getErrorMessage();
        this.httpStatus = apiErrorType.getHttpStatus();
    }
}
