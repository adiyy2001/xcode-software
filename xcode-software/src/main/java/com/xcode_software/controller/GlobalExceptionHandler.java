package com.xcode_software.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.xcode_software.controller.dto.ApiResponse;
import com.xcode_software.service.CurrencyNotFoundException;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CurrencyNotFoundException.class)
    public ResponseEntity<ApiResponse<?>> handleCurrencyNotFound(CurrencyNotFoundException ex) {
        return buildResponse(HttpStatus.NOT_FOUND, ex.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<?>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            errors.put(error.getField(), error.getDefaultMessage());
        }
        return buildResponse(HttpStatus.BAD_REQUEST, "Validation failed", errors);
    }
    

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<?>> handleGeneralException(Exception ex) {
        return buildResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Unexpected error occurred.");
    }

    private ResponseEntity<ApiResponse<?>> buildResponse(HttpStatus status, String message) {
        ApiResponse<?> response = ApiResponse.builder()
                .success(false)
                .message(message)
                .build();
        return ResponseEntity.status(status).body(response);
    }

    private ResponseEntity<ApiResponse<?>> buildResponse(HttpStatus status, String message, Object details) {
        ApiResponse<?> response = ApiResponse.builder()
                .success(false)
                .message(message)
                .details(details)
                .build();
        return ResponseEntity.status(status).body(response);
    }
}
