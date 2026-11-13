package org.minibank.model;

import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

@Getter
public class CheckingAccount extends Account {

    private final BigDecimal overdraftLimit;

    public CheckingAccount(String id, String ownerId, Currency currency, LocalDateTime createAt, BigDecimal balance, BigDecimal overdraftLimit) {
        super(id, ownerId, currency, createAt, balance);
        this.overdraftLimit = Objects.requireNonNull(overdraftLimit, "Overdraft Limit must not be null");
        if (overdraftLimit.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Overdraft Limit must be positive");
        }
    }

    @Override
    public BigDecimal getAvailableBalance() {
        return getBalance().add(overdraftLimit);
    }
}
