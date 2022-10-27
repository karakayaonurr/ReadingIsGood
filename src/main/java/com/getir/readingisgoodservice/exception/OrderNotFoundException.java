package com.getir.readingisgoodservice.exception;

public class OrderNotFoundException extends BaseRuntimeException {

    public OrderNotFoundException(ApiErrorType apiErrorType) {
        super(apiErrorType);
    }
}
