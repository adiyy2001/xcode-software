package com.xcode_software.service;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.xcode_software.service.dto.NbpResponse;

import java.util.Optional;
import java.util.logging.Logger;

@Component
public class NbpApiClient {

    private final RestTemplate restTemplate;
    private static final Logger LOGGER = Logger.getLogger(NbpApiClient.class.getName());

    public NbpApiClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Optional<Double> getCurrencyValue(String currencyCode) {
        String url = String.format("https://api.nbp.pl/api/exchangerates/rates/a/%s/?format=json", currencyCode);
        try {
            LOGGER.info("Fetching data from NBP API: " + url);
            NbpResponse response = restTemplate.getForObject(url, NbpResponse.class);
    
            if (response != null && response.getRates() != null && !response.getRates().isEmpty()) {
                LOGGER.info("Received response with rates: " + response.getRates());
                return Optional.of(response.getRates().get(0).getMid());
            } else {
                LOGGER.warning("No rates found in response or response is null.");
            }
        } catch (Exception e) {
            LOGGER.severe("Exception occurred while fetching data: " + e.getMessage());
        }
        return Optional.empty();
    }
    
    
}
