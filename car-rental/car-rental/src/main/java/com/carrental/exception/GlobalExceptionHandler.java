package com.carrental.exception;

import com.carrental.dto.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleRuntimeException(
            RuntimeException ex) {

        return ErrorResponse.builder()
                .message(ex.getMessage())
                .status(HttpStatus.BAD_REQUEST.value())
                .timestamp(LocalDateTime.now())
                .build();
    }

    @ExceptionHandler(
            MethodArgumentNotValidException.class
    )
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleValidationException(
            MethodArgumentNotValidException ex) {

        String message =
                ex.getBindingResult()
                        .getFieldError()
                        .getDefaultMessage();

        return ErrorResponse.builder()
                .message(message)
                .status(HttpStatus.BAD_REQUEST.value())
                .timestamp(LocalDateTime.now())
                .build();
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(
            HttpStatus.INTERNAL_SERVER_ERROR
    )
    public ErrorResponse handleGenericException(
            Exception ex) {

        return ErrorResponse.builder()
                .message("Something went wrong")
                .status(
                        HttpStatus
                                .INTERNAL_SERVER_ERROR
                                .value()
                )
                .timestamp(LocalDateTime.now())
                .build();
    }
}