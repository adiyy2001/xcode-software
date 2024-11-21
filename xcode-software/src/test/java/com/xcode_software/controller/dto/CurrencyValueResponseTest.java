package com.xcode_software.controller.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CurrencyValueResponseTest {

    @Test
    void testConstructorAndGetter() {
        // Arrange
        Double value = 4.23;

        // Act
        CurrencyValueResponse response = new CurrencyValueResponse(value);

        // Assert
        assertEquals(value, response.getValue());
    }

    @Test
    void testSetter() {
        // Arrange
        CurrencyValueResponse response = new CurrencyValueResponse(4.23);
        Double newValue = 4.56;

        // Act
        response.setValue(newValue);

        // Assert
        assertEquals(newValue, response.getValue());
    }
}
