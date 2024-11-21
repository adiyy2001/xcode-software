package com.xcode_software.controller.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ApiResponseTest {

    @Test
    void testSuccessResponse() {
        // Arrange
        String message = "Operation successful";
        String details = "Details of success";

        // Act
        ApiResponse<String> response = ApiResponse.success(message, details);

        // Assert
        assertTrue(response.isSuccess());
        assertEquals(200, response.getStatusCode());
        assertEquals(message, response.getMessage());
        assertEquals(details, response.getDetails());
    }

    @Test
    void testFailureResponse() {
        // Arrange
        String message = "Operation failed";
        int statusCode = 400;

        // Act
        ApiResponse<?> response = ApiResponse.failure(message, statusCode);

        // Assert
        assertFalse(response.isSuccess());
        assertEquals(statusCode, response.getStatusCode());
        assertEquals(message, response.getMessage());
        assertNull(response.getDetails());
    }

    @Test
    void testBuilderWithDetails() {
        // Arrange
        boolean success = true;
        String message = "Builder test successful";
        int statusCode = 201;
        String details = "Builder details";

        // Act
        ApiResponse<String> response = ApiResponse.<String>builder()
                .success(success)
                .statusCode(statusCode)
                .message(message)
                .details(details)
                .build();

        // Assert
        assertTrue(response.isSuccess());
        assertEquals(statusCode, response.getStatusCode());
        assertEquals(message, response.getMessage());
        assertEquals(details, response.getDetails());
    }

    @Test
    void testBuilderWithoutDetails() {
        // Arrange
        boolean success = false;
        String message = "Builder test failed";
        int statusCode = 404;

        // Act
        ApiResponse<?> response = ApiResponse.builder()
                .success(success)
                .statusCode(statusCode)
                .message(message)
                .build();

        // Assert
        assertFalse(response.isSuccess());
        assertEquals(statusCode, response.getStatusCode());
        assertEquals(message, response.getMessage());
        assertNull(response.getDetails());
    }
}
