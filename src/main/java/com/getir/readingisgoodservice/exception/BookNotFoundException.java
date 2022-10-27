package com.getir.readingisgoodservice.exception;

public class BookNotFoundException extends BaseRuntimeException {

    public BookNotFoundException(ApiErrorType apiErrorType) {
        super(apiErrorType);
    }
}
