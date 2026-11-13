package org.minibank.model;

import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

@Getter
public class SavingsAccount extends Account {

    private final BigDecimal interestRate;
    private final int monthlyWithdrawalLimit;

    public SavingsAccount(String id, String ownerId, Currency currency, LocalDateTime createAt, BigDecimal balance, BigDecimal interestRate, int monthlyWithdrawalLimit) {
        super(id, ownerId, currency, createAt, balance);
        this.interestRate = Objects.requireNonNull(interestRate, "Interest Rate must not be null");
        this.monthlyWithdrawalLimit = monthlyWithdrawalLimit;
    }

    @Override
    public BigDecimal getAvailableBalance() {
        return getBalance();
    }
}
