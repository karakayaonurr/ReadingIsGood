package com.getir.readingisgoodservice.exception;

import com.getir.readingisgoodservice.model.ApiErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import static com.getir.readingisgoodservice.exception.ApiErrorType.INTERNAL_SERVER_ERROR;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BaseRuntimeException.class)
    public ResponseEntity<ApiErrorResponse> handleBaseRuntimeException(BaseRuntimeException exception) {
        log.error("Api exception occurred. Exception: {}, errorCode: {}, errorMessage: {}",
                exception.getClass().getName(),
                exception.getErrorCode(),
                exception.getErrorMessage());

        return createErrorResponse(INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ApiErrorResponse> handleRuntimeException(RuntimeException exception) {
        log.error("Api exception occurred. Exception: {}, errorCode: {}, errorMessage: {}",
                exception.getClass().getName(),
                INTERNAL_SERVER_ERROR.getErrorCode(),
                exception.getMessage());

        return createErrorResponse(INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        log.error("Api exception occurred. Exception: {}, errorCode: {}, errorMessage: {}",
                exception.getClass().getName(),
                HttpStatus.BAD_REQUEST.value(),
                HttpStatus.BAD_REQUEST.getReasonPhrase());

        return createErrorResponse(ApiErrorType.FIELD_VALIDATION_ERROR.getHttpStatus(),
                exception.getFieldErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).toList().toString(),
                ApiErrorType.FIELD_VALIDATION_ERROR.getErrorCode());
    }

    private ResponseEntity<ApiErrorResponse> createErrorResponse(ApiErrorType apiErrorType) {
        ApiErrorResponse apiErrorResponse = ApiErrorResponse.builder()
                .errorCode(String.valueOf(apiErrorType.getErrorCode()))
                .errorMessage(apiErrorType.getErrorMessage())
                .errorStatus(apiErrorType.getHttpStatus())
                .build();

        return ResponseEntity.
                status(apiErrorType.getHttpStatus()).
                body(apiErrorResponse);
    }

    private ResponseEntity<ApiErrorResponse> createErrorResponse(HttpStatus httpStatus, String errorMessage, int errorCode) {
        ApiErrorResponse apiErrorResponse = ApiErrorResponse.builder()
                .errorCode(String.valueOf(errorCode))
                .errorMessage(errorMessage)
                .errorStatus(httpStatus)
                .build();

        return ResponseEntity.
                status(httpStatus).
                body(apiErrorResponse);
    }
}
