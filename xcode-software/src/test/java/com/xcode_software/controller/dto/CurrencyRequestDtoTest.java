package com.xcode_software.controller.dto;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class CurrencyRequestDtoTest {

    private Validator validator;

    @BeforeEach
    void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void testValidCurrencyRequestDto() {
        // Arrange
        CurrencyRequestDto dto = new CurrencyRequestDto("USD", "John Doe");

        // Act
        Set<ConstraintViolation<CurrencyRequestDto>> violations = validator.validate(dto);

        // Assert
        assertTrue(violations.isEmpty(), "No violations should occur for valid input");
    }

    @Test
    void testInvalidCurrencyRequestDto_MissingCurrency() {
        // Arrange
        CurrencyRequestDto dto = new CurrencyRequestDto("", "John Doe");

        // Act
        Set<ConstraintViolation<CurrencyRequestDto>> violations = validator.validate(dto);

        // Assert
        assertFalse(violations.isEmpty(), "Violations should occur for missing currency");
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().equals("Currency is required.")));
    }

    @Test
    void testInvalidCurrencyRequestDto_MissingName() {
        // Arrange
        CurrencyRequestDto dto = new CurrencyRequestDto("USD", "");

        // Act
        Set<ConstraintViolation<CurrencyRequestDto>> violations = validator.validate(dto);

        // Assert
        assertFalse(violations.isEmpty(), "Violations should occur for missing name");
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().equals("Name is required.")));
    }

    @Test
    void testInvalidCurrencyRequestDto_MissingBothFields() {
        // Arrange
        CurrencyRequestDto dto = new CurrencyRequestDto("", "");

        // Act
        Set<ConstraintViolation<CurrencyRequestDto>> violations = validator.validate(dto);

        // Assert
        assertEquals(2, violations.size(), "Two violations should occur for missing currency and name");
    }

    @Test
    void testSettersAndGetters() {
        // Arrange
        CurrencyRequestDto dto = new CurrencyRequestDto();

        // Act
        dto.setCurrency("EUR");
        dto.setName("Jane Doe");

        // Assert
        assertEquals("EUR", dto.getCurrency());
        assertEquals("Jane Doe", dto.getName());
    }
}
