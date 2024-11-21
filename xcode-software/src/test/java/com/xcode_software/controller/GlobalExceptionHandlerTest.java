package com.xcode_software.controller;

import com.xcode_software.controller.dto.ApiResponse;
import com.xcode_software.service.CurrencyNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

class GlobalExceptionHandlerTest {

    private GlobalExceptionHandler exceptionHandler;

    @BeforeEach
    void setUp() {
        exceptionHandler = new GlobalExceptionHandler();
    }

    @Test
    void testHandleCurrencyNotFound() {
        // Arrange
        String errorMessage = "Currency not found";
        CurrencyNotFoundException exception = new CurrencyNotFoundException(errorMessage);

        // Act
        ResponseEntity<ApiResponse<?>> response = exceptionHandler.handleCurrencyNotFound(exception);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals(errorMessage, response.getBody().getMessage());
        assertEquals(false, response.getBody().isSuccess());
    }

    @Test
    void testHandleValidationExceptions() {
        // Arrange
        BeanPropertyBindingResult bindingResult = new BeanPropertyBindingResult(null, "objectName");
        bindingResult.addError(new FieldError("objectName", "field", "Field is invalid"));
        MethodArgumentNotValidException exception = new MethodArgumentNotValidException(null, bindingResult);

        // Act
        ResponseEntity<ApiResponse<?>> response = exceptionHandler.handleValidationExceptions(exception);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Validation failed", response.getBody().getMessage());
        assertEquals(false, response.getBody().isSuccess());
        assertEquals(Collections.singletonMap("field", "Field is invalid"), response.getBody().getDetails());
    }

    @Test
    void testHandleGeneralException() {
        // Arrange
        String errorMessage = "Unexpected error occurred.";
        Exception exception = new Exception("Some error");

        // Act
        ResponseEntity<ApiResponse<?>> response = exceptionHandler.handleGeneralException(exception);

        // Assert
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals(errorMessage, response.getBody().getMessage());
        assertEquals(false, response.getBody().isSuccess());
    }
}
