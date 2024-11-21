package com.xcode_software.controller;

import com.xcode_software.controller.dto.ApiResponse;
import com.xcode_software.controller.dto.CurrencyRequestDto;
import com.xcode_software.controller.dto.CurrencyRequestResponseDto;
import com.xcode_software.controller.dto.CurrencyValueResponse;
import com.xcode_software.service.CurrencyService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

class CurrencyControllerTest {

    @Mock
    private CurrencyService currencyService;

    @InjectMocks
    private CurrencyController currencyController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetCurrentCurrencyValue() {
        // Arrange
        CurrencyRequestDto requestDto = new CurrencyRequestDto("USD", "John Doe");
        Double expectedValue = 4.23;

        // Mockowanie metod serwisu
        when(currencyService.getCurrentCurrencyValue(eq("USD"))).thenReturn(expectedValue);
        when(currencyService.saveCurrencyRequest(eq("USD"), eq("John Doe"), eq(expectedValue)))
                .thenReturn(null); // Jeśli metoda zwraca `void`, użyj `.thenReturn(null)`.

        // Act
        ResponseEntity<CurrencyValueResponse> response = currencyController.getCurrentCurrencyValue(requestDto);

        // Assert
        assertNotNull(response);
        assertEquals(200, response.getStatusCode().value());
        assertNotNull(response.getBody());
        assertEquals(expectedValue, response.getBody().getValue());
        verify(currencyService, times(1)).getCurrentCurrencyValue(eq("USD"));
        verify(currencyService, times(1)).saveCurrencyRequest(eq("USD"), eq("John Doe"), eq(expectedValue));

    }

    @Test
    void testGetAllRequests() {
        // Arrange
        CurrencyRequestResponseDto responseDto1 = new CurrencyRequestResponseDto("USD", "John Doe", LocalDateTime.now(),
                4.23);
        CurrencyRequestResponseDto responseDto2 = new CurrencyRequestResponseDto("EUR", "Jane Doe", LocalDateTime.now(),
                4.56);
        List<CurrencyRequestResponseDto> expectedResponse = Arrays.asList(responseDto1, responseDto2);

        when(currencyService.getAllRequests(0, 10)).thenReturn(expectedResponse);

        // Act
        ResponseEntity<ApiResponse<List<CurrencyRequestResponseDto>>> response = currencyController.getAllRequests(0,
                10);

        // Assert
        assertNotNull(response);
        assertEquals(200, response.getStatusCode().value());
        assertEquals("Requests fetched successfully", response.getBody().getMessage());
        assertEquals(expectedResponse, response.getBody().getDetails());
        verify(currencyService, times(1)).getAllRequests(0, 10);
    }

}
