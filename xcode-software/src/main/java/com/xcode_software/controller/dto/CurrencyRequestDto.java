package com.xcode_software.controller.dto;

import jakarta.validation.constraints.NotBlank;

public class CurrencyRequestDto {

    @NotBlank(message = "Currency is required.")
    private String currency;

    @NotBlank(message = "Name is required.")
    private String name;

    public CurrencyRequestDto() {}

    public CurrencyRequestDto(String currency, String name) {
        this.currency = currency;
        this.name = name;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
