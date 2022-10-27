package com.getir.readingisgoodservice.exception;

public class StatisticsNotFoundException extends BaseRuntimeException {

    public StatisticsNotFoundException(ApiErrorType apiErrorType) {
        super(apiErrorType);
    }
}
