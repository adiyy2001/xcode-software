package com.xcode_software.controller.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ErrorResponseTest {

    @Test
    void testConstructorAndGetter() {
        // Arrange
        String errorMessage = "An error occurred";

        // Act
        ErrorResponse errorResponse = new ErrorResponse(errorMessage);

        // Assert
        assertEquals(errorMessage, errorResponse.getError());
    }

    @Test
    void testSetter() {
        // Arrange
        ErrorResponse errorResponse = new ErrorResponse("Initial error");
        String newErrorMessage = "Updated error";

        // Act
        errorResponse.setError(newErrorMessage);

        // Assert
        assertEquals(newErrorMessage, errorResponse.getError());
    }
}
