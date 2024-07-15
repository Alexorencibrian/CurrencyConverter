package com.Allura.currencyconverter.model;

public enum Currency {
    ARS("Peso argentino"),
    BOB("Boliviano boliviano"),
    BRL("Real brasileño"),
    CLP("Peso chileno"),
    COP("Peso colombiano"),
    USD("Dólar estadounidense");

    private final String description;

    Currency(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
