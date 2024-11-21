package com.xcode_software.service.dto;

import java.util.List;

public class NbpResponse {
    private List<Rate> rates;

    public List<Rate> getRates() {
        return rates;
    }

    public void setRates(List<Rate> rates) {
        this.rates = rates;
    }

    public static class Rate {
        private Double mid;

        public Double getMid() {
            return mid;
        }

        public void setMid(Double mid) {
            this.mid = mid;
        }
    }
}
