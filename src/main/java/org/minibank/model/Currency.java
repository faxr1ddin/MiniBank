package org.minibank.model;

public enum Currency {
    UZS("UZS", "So'm"), USD("USD", "$"), EUR("EUR", "€");

    private final String code;
    private final String symbol;

    Currency(String code, String symbol) {
        this.code = code;
        this.symbol = symbol;
    }

    public String getCode() {
        return code;
    }

    public String getSymbol() {
        return symbol;
    }
}
