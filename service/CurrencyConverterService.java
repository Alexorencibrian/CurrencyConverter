package com.Allura.currencyconverter.service;

import com.Allura.currencyconverter.model.Currency;
import com.Allura.currencyconverter.model.ExchangeRateResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Map;

@Service
public class CurrencyConverterService {

    @Autowired
    private ApiClientService apiClient;

    public BigDecimal convert(Currency fromCurrency, Currency toCurrency, BigDecimal amount) {
        ExchangeRateResponse response = apiClient.getExchangeRates();
        Map<String, Double> rates = response.getConversion_rates();

        String fromCode = fromCurrency.name();
        String toCode = toCurrency.name();

        if (!rates.containsKey(fromCode) || !rates.containsKey(toCode)) {
            throw new IllegalArgumentException("Invalid currency code");
        }

        double fromRate = rates.get(fromCode);
        double toRate = rates.get(toCode);

        return amount.multiply(BigDecimal.valueOf(toRate / fromRate))
                .setScale(2, RoundingMode.HALF_UP);
    }
}
