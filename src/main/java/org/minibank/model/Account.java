package org.minibank.model;

import lombok.Getter;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@ToString
public abstract class Account {
    private final String id;
    private final String ownerId;
    private final Currency currency;
    private BigDecimal balance;
    private final LocalDateTime createAt;

    protected Account(String id, String ownerId, Currency currency, LocalDateTime createAt, BigDecimal balance) {
        this.id = Objects.requireNonNull(id, "ID must not be null");
        this.ownerId = Objects.requireNonNull(ownerId, "Owner ID must not be null");
        this.currency = Objects.requireNonNull(currency, "Currency must not be null");
        this.createAt = Objects.requireNonNull(createAt, "Create Time must not be null");
        this.balance = Objects.requireNonNull(balance, "Balance must not be null");
        if (balance.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Amount must be positive");
        }
    }

    public abstract BigDecimal getAvailableBalance();

    @Override
    public final boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Account other)) return false;

        return id.equals(other.id);
    }

    @Override
    public final int hashCode() {
        return id.hashCode();
    }
}
