package com.xcode_software.controller.dto;

import java.time.LocalDateTime;
import java.util.Objects;

public class CurrencyRequestResponseDto {
    private String currency;
    private String name;
    private LocalDateTime date;
    private Double value;

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        CurrencyRequestResponseDto that = (CurrencyRequestResponseDto) o;
        return Objects.equals(currency, that.currency) &&
                Objects.equals(name, that.name) &&
                Objects.equals(date, that.date) &&
                Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(currency, name, date, value);
    }

    public CurrencyRequestResponseDto(String currency, String name, LocalDateTime date, Double value) {
        this.currency = currency;
        this.name = name;
        this.date = date;
        this.value = value;
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

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }
}
