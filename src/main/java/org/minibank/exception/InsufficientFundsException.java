package org.minibank.exception;

import java.math.BigDecimal;

public class InsufficientFundsException extends BankException {
    private final BigDecimal requestedAmount;
    private final BigDecimal availableAmount;

    public InsufficientFundsException(BigDecimal requestedAmount, BigDecimal availableAmount) {
        super("Insufficient funds. Requested: " + requestedAmount + ", Available: " + availableAmount);
        this.requestedAmount = requestedAmount;
        this.availableAmount = availableAmount;
    }

    public BigDecimal getRequestedAmount() {
        return requestedAmount;
    }

    public BigDecimal getAvailableAmount() {
        return availableAmount;
    }
}
