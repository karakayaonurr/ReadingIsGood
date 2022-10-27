package com.getir.readingisgoodservice.exception;

import com.getir.readingisgoodservice.model.ApiErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import static com.getir.readingisgoodservice.exception.ApiErrorType.BOOK_EXIST_EXCEPTION;
import static com.getir.readingisgoodservice.exception.ApiErrorType.BOOK_NOT_FOUND_EXCEPTION;
import static com.getir.readingisgoodservice.exception.ApiErrorType.CUSTOMER_EXISTS_EXCEPTION;
import static com.getir.readingisgoodservice.exception.ApiErrorType.CUSTOMER_NOT_FOUND_EXCEPTION;
import static com.getir.readingisgoodservice.exception.ApiErrorType.FIELD_VALIDATION_EXCEPTION;
import static com.getir.readingisgoodservice.exception.ApiErrorType.INTERNAL_SERVER_EXCEPTION;
import static com.getir.readingisgoodservice.exception.ApiErrorType.ORDER_NOT_FOUND_EXCEPTION;
import static com.getir.readingisgoodservice.exception.ApiErrorType.STATISTICS_NOT_FOUND_EXCEPTION;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BaseRuntimeException.class)
    public ResponseEntity<ApiErrorResponse> handleBaseRuntimeException(BaseRuntimeException exception) {
        log.error("Api exception occurred. Exception: {}, errorCode: {}, errorMessage: {}",
                exception.getClass().getName(),
                exception.getErrorCode(),
                exception.getErrorMessage());

        return createErrorResponse(INTERNAL_SERVER_EXCEPTION);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ApiErrorResponse> handleRuntimeException(RuntimeException exception) {
        log.error("Api exception occurred. Exception: {}, errorCode: {}, errorMessage: {}",
                exception.getClass().getName(),
                INTERNAL_SERVER_EXCEPTION.getErrorCode(),
                exception.getMessage());

        return createErrorResponse(INTERNAL_SERVER_EXCEPTION);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        log.error("Api exception occurred. Exception: {}, errorCode: {}, errorMessage: {}",
                exception.getClass().getName(),
                FIELD_VALIDATION_EXCEPTION.getErrorCode(),
                FIELD_VALIDATION_EXCEPTION.getErrorMessage());

        return createErrorResponse(FIELD_VALIDATION_EXCEPTION.getHttpStatus(),
                exception.getFieldErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).toList().toString(),
                FIELD_VALIDATION_EXCEPTION.getErrorCode());
    }

    @ExceptionHandler(BookAlreadyExistException.class)
    public ResponseEntity<ApiErrorResponse> handleBookAlreadyExistException(BookAlreadyExistException exception) {
        log.error("Api exception occurred. Exception: {}, errorCode: {}, errorMessage: {}",
                exception.getClass().getName(),
                exception.getErrorCode(),
                exception.getErrorMessage());

        return createErrorResponse(BOOK_EXIST_EXCEPTION);
    }

    @ExceptionHandler(BookNotFoundException.class)
    public ResponseEntity<ApiErrorResponse> handleBookNotFoundException(BookNotFoundException exception) {
        log.error("Api exception occurred. Exception: {}, errorCode: {}, errorMessage: {}",
                exception.getClass().getName(),
                exception.getErrorCode(),
                exception.getErrorMessage());

        return createErrorResponse(BOOK_NOT_FOUND_EXCEPTION);
    }

    @ExceptionHandler(CustomerAlreadyExistException.class)
    public ResponseEntity<ApiErrorResponse> handleCustomerAlreadyExistException(CustomerAlreadyExistException exception) {
        log.error("Api exception occurred. Exception: {}, errorCode: {}, errorMessage: {}",
                exception.getClass().getName(),
                exception.getErrorCode(),
                exception.getErrorMessage());

        return createErrorResponse(CUSTOMER_EXISTS_EXCEPTION);
    }

    @ExceptionHandler(CustomerNotFoundException.class)
    public ResponseEntity<ApiErrorResponse> handleCustomerNotFoundException(CustomerNotFoundException exception) {
        log.error("Api exception occurred. Exception: {}, errorCode: {}, errorMessage: {}",
                exception.getClass().getName(),
                exception.getErrorCode(),
                exception.getErrorMessage());

        return createErrorResponse(CUSTOMER_NOT_FOUND_EXCEPTION);
    }

    @ExceptionHandler(OrderNotFoundException.class)
    public ResponseEntity<ApiErrorResponse> handleOrderNotFoundException(OrderNotFoundException exception) {
        log.error("Api exception occurred. Exception: {}, errorCode: {}, errorMessage: {}",
                exception.getClass().getName(),
                exception.getErrorCode(),
                exception.getErrorMessage());

        return createErrorResponse(ORDER_NOT_FOUND_EXCEPTION);
    }

    @ExceptionHandler(StatisticsNotFoundException.class)
    public ResponseEntity<ApiErrorResponse> handleStatisticsNotFoundException(StatisticsNotFoundException exception) {
        log.error("Api exception occurred. Exception: {}, errorCode: {}, errorMessage: {}",
                exception.getClass().getName(),
                exception.getErrorCode(),
                exception.getErrorMessage());

        return createErrorResponse(STATISTICS_NOT_FOUND_EXCEPTION);
    }

    private ResponseEntity<ApiErrorResponse> createErrorResponse(ApiErrorType apiErrorType) {
        ApiErrorResponse apiErrorResponse = ApiErrorResponse.builder()
                .errorCode(String.valueOf(apiErrorType.getErrorCode()))
                .errorMessage(apiErrorType.getErrorMessage())
                .errorStatus(apiErrorType.getHttpStatus())
                .build();

        return ResponseEntity
                .status(apiErrorType.getHttpStatus())
                .body(apiErrorResponse);
    }

    private ResponseEntity<ApiErrorResponse> createErrorResponse(HttpStatus httpStatus, String errorMessage, int errorCode) {
        ApiErrorResponse apiErrorResponse = ApiErrorResponse.builder()
                .errorCode(String.valueOf(errorCode))
                .errorMessage(errorMessage)
                .errorStatus(httpStatus)
                .build();

        return ResponseEntity
                .status(httpStatus)
                .body(apiErrorResponse);
    }
}
