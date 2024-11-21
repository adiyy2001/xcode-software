package com.xcode_software.model;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class CurrencyRequestTest {

    @Test
    void testConstructorAndGetters() {
        // Arrange
        String currency = "USD";
        String name = "John Doe";
        LocalDateTime date = LocalDateTime.now();
        Double value = 4.23;

        // Act
        CurrencyRequest request = new CurrencyRequest(currency, name, date, value);

        // Assert
        assertNull(request.getId(), "ID should be null for a new instance");
        assertEquals(currency, request.getCurrency());
        assertEquals(name, request.getName());
        assertEquals(date, request.getDate());
        assertEquals(value, request.getValue());
    }

    @Test
    void testSetters() {
        // Arrange
        CurrencyRequest request = new CurrencyRequest();
        Long id = 1L;
        String currency = "EUR";
        String name = "Jane Doe";
        LocalDateTime date = LocalDateTime.now();
        Double value = 5.67;

        // Act
        request.setId(id);
        request.setCurrency(currency);
        request.setName(name);
        request.setDate(date);
        request.setValue(value);

        // Assert
        assertEquals(id, request.getId());
        assertEquals(currency, request.getCurrency());
        assertEquals(name, request.getName());
        assertEquals(date, request.getDate());
        assertEquals(value, request.getValue());
    }
}
