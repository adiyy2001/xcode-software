package com.xcode_software.service;

import com.xcode_software.controller.dto.CurrencyRequestResponseDto;
import com.xcode_software.model.CurrencyRequest;
import com.xcode_software.repository.CurrencyRequestRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CurrencyService {

    private final CurrencyRequestRepository repository;
    private final NbpApiClient nbpApiClient;

    @Autowired
    public CurrencyService(CurrencyRequestRepository repository, NbpApiClient nbpApiClient) {
        this.repository = repository;
        this.nbpApiClient = nbpApiClient;
    }

    public Double getCurrentCurrencyValue(String currencyCode) {
        return nbpApiClient.getCurrencyValue(currencyCode)
                .orElseThrow(() -> new CurrencyNotFoundException("Currency not found: " + currencyCode));
    }

    public CurrencyRequest saveCurrencyRequest(String currency, String name, Double value) {
        CurrencyRequest request = new CurrencyRequest(currency, name, LocalDateTime.now(), value);
        return repository.save(request);
    }

    public List<CurrencyRequestResponseDto> getAllRequests(int page, int size) {
        return repository.findAll(PageRequest.of(page, size))
                .stream()
                .map(request -> new CurrencyRequestResponseDto(
                        request.getCurrency(),
                        request.getName(),
                        request.getDate(),
                        request.getValue()))
                .toList();
    }
    

}
