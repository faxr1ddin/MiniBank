package org.minibank.model;

import lombok.Getter;
import lombok.ToString;
import org.minibank.exception.InvalidAmountException;

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
    private final LocalDateTime createdAt;

    protected Account(String id, String ownerId, Currency currency, LocalDateTime createdAt, BigDecimal balance) {
        this.id = Objects.requireNonNull(id, "ID must not be null");
        this.ownerId = Objects.requireNonNull(ownerId, "Owner ID must not be null");
        this.currency = Objects.requireNonNull(currency, "Currency must not be null");
        this.createdAt = Objects.requireNonNull(createdAt, "Create Time must not be null");
        this.balance = Objects.requireNonNull(balance, "Balance must not be null");
        if (balance.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Amount must be positive");
        }
    }

    public abstract BigDecimal getAvailableBalance();

    public void deposit(BigDecimal amount) {
        validateAmount(amount);
        this.balance = this.balance.add(amount);
    }

    public final void withdraw(BigDecimal amount) {
        validateAmount(amount);
        checkWithdrawalAllowed(amount);
        this.balance = this.balance.subtract(amount);
    }

    protected abstract void checkWithdrawalAllowed(BigDecimal amount);

    private void validateAmount(BigDecimal amount) {
        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new InvalidAmountException("Amount must be postive");
        }
    }

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
