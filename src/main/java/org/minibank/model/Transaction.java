package org.minibank.model;

import org.jetbrains.annotations.Nullable;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

public record Transaction(long id, TransactionType type, BigDecimal amount, Currency currency, LocalDateTime timestamp,
                          @Nullable String fromAccountId, @Nullable String toAccountId, TransactionStatus status) {
    public Transaction {
        Objects.requireNonNull(amount, "Amount cannot be null");
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Amount must be positive");
        }
        Objects.requireNonNull(type, "Type cannot be null");
        Objects.requireNonNull(currency, "Currency cannot be null");
        Objects.requireNonNull(timestamp, "Date cannot be null");
        Objects.requireNonNull(status, "Status cannot be null");
    }

    public static Transaction deposit(long id, BigDecimal amount, Currency currency, String toAccountId) {
        return new Transaction(id, TransactionType.DEPOSIT, amount, currency, LocalDateTime.now(), null, toAccountId, TransactionStatus.COMPLETED);
    }

    public static Transaction withdrawal(long id, BigDecimal amount, Currency currency, String fromAccountId, String toAccountId) {
        return new Transaction(id, TransactionType.WITHDRAWAL, amount, currency, LocalDateTime.now(), fromAccountId, toAccountId, TransactionStatus.COMPLETED);
    }

    public static Transaction transfer(long id, BigDecimal amount, Currency currency, String fromAccountId, String toAccountId) {
        return new Transaction(id, TransactionType.TRANSFER, amount, currency, LocalDateTime.now(), fromAccountId, toAccountId, TransactionStatus.COMPLETED);
    }

    public static Transaction interest(long id, BigDecimal amount, Currency currency, String toAccountId) {
        return new Transaction(id, TransactionType.INTEREST, amount, currency, LocalDateTime.now(), null, toAccountId, TransactionStatus.COMPLETED);
    }
}
