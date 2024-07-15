package com.Allura.currencyconverter.controller;

import com.Allura.currencyconverter.model.Currency;
import com.Allura.currencyconverter.service.CurrencyConverterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
public class CurrencyConverterController {

    @Autowired
    private CurrencyConverterService converterService;

    @GetMapping("/convert")
    public ResponseEntity<?> convertCurrency(
            @RequestParam Currency fromCurrency,
            @RequestParam Currency toCurrency,
            @RequestParam BigDecimal amount) {
        try {
            BigDecimal result = converterService.convert(fromCurrency, toCurrency, amount);
            return ResponseEntity.ok(result);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("An error occurred during conversion");
        }
    }
}
