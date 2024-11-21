package com.xcode_software.controller.dto;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class CurrencyRequestResponseDtoTest {

    @Test
    void testConstructorAndGetters() {
        // Arrange
        String currency = "USD";
        String name = "John Doe";
        LocalDateTime date = LocalDateTime.now();
        Double value = 4.23;

        // Act
        CurrencyRequestResponseDto dto = new CurrencyRequestResponseDto(currency, name, date, value);

        // Assert
        assertEquals(currency, dto.getCurrency());
        assertEquals(name, dto.getName());
        assertEquals(date, dto.getDate());
        assertEquals(value, dto.getValue());
    }

    @Test
    void testSetters() {
        // Arrange
        CurrencyRequestResponseDto dto = new CurrencyRequestResponseDto("USD", "John Doe", LocalDateTime.now(), 4.23);
        String newCurrency = "EUR";
        String newName = "Jane Doe";
        LocalDateTime newDate = LocalDateTime.now();
        Double newValue = 4.56;

        // Act
        dto.setCurrency(newCurrency);
        dto.setName(newName);
        dto.setDate(newDate);
        dto.setValue(newValue);

        // Assert
        assertEquals(newCurrency, dto.getCurrency());
        assertEquals(newName, dto.getName());
        assertEquals(newDate, dto.getDate());
        assertEquals(newValue, dto.getValue());
    }
}
