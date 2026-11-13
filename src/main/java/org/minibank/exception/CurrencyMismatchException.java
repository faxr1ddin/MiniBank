package org.minibank.exception;

public class CurrencyMismatchException extends BankException {

    public CurrencyMismatchException(String message) {
        super(message);
    }
}
