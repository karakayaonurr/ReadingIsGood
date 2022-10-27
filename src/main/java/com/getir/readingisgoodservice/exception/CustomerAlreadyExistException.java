package com.getir.readingisgoodservice.exception;

public class CustomerAlreadyExistException extends BaseRuntimeException {

    public CustomerAlreadyExistException(ApiErrorType apiErrorType) {
        super(apiErrorType);
    }
}
