package com.Allura.currencyconverter.service;

import com.Allura.currencyconverter.model.ExchangeRateResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ApiClientService {

    @Value("${exchangerate.api.key}")
    private String apiKey;

    @Value("${exchangerate.api.url}")
    private String apiUrl;

    private final RestTemplate restTemplate;

    public ApiClientService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public ExchangeRateResponse getExchangeRates() {
        String url = apiUrl + apiKey + "/latest/USD";
        return restTemplate.getForObject(url, ExchangeRateResponse.class);
    }
}
