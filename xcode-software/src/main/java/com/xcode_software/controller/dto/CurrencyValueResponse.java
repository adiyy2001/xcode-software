package com.xcode_software.controller.dto;

public class CurrencyValueResponse {
    private Double value;

    public CurrencyValueResponse(Double value) {
        this.value = value;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }
}
