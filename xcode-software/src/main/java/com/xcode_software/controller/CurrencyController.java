package com.xcode_software.controller;

import com.xcode_software.controller.dto.ApiResponse;
import com.xcode_software.controller.dto.CurrencyRequestDto;
import com.xcode_software.controller.dto.CurrencyRequestResponseDto;
import com.xcode_software.controller.dto.CurrencyValueResponse;
import com.xcode_software.service.CurrencyService;
import jakarta.validation.Valid;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/currencies")
public class CurrencyController {

    private final CurrencyService service;

    @Autowired
    public CurrencyController(CurrencyService service) {
        this.service = service;
    }

    @PostMapping("/get-current-currency-value-command")
    public ResponseEntity<CurrencyValueResponse> getCurrentCurrencyValue(@Valid @RequestBody CurrencyRequestDto requestDto) {
        Double value = service.getCurrentCurrencyValue(requestDto.getCurrency());
        service.saveCurrencyRequest(requestDto.getCurrency(), requestDto.getName(), value);
        return ResponseEntity.ok(new CurrencyValueResponse(value));
    }

    @GetMapping("/requests")
    public ResponseEntity<ApiResponse<List<CurrencyRequestResponseDto>>> getAllRequests(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        var requests = service.getAllRequests(page, size)
                .stream()
                .map(request -> new CurrencyRequestResponseDto(
                        request.getCurrency(),
                        request.getName(),
                        request.getDate(),
                        request.getValue()))
                .toList();
        return ResponseEntity.ok(ApiResponse.success("Requests fetched successfully", requests));
    }
    
}
