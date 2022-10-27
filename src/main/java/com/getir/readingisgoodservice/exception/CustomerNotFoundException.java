package com.getir.readingisgoodservice.exception;

public class CustomerNotFoundException extends BaseRuntimeException {

    public CustomerNotFoundException(ApiErrorType apiErrorType) {
        super(apiErrorType);
    }
}
