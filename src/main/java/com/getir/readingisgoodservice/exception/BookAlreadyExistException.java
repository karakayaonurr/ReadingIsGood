package com.getir.readingisgoodservice.exception;

public class BookAlreadyExistException extends BaseRuntimeException {

    public BookAlreadyExistException(ApiErrorType apiErrorType) {
        super(apiErrorType);
    }
}
