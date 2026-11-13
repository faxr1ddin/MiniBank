package org.minibank.service;

import org.minibank.exception.CurrencyMismatchException;
import org.minibank.model.Account;
import org.minibank.model.Transaction;

import java.math.BigDecimal;

public class TransactionService {

    private long transactionIdCounter = 0;

    public Transaction deposit(Account account, BigDecimal amount) {
        account.deposit(amount);

        return Transaction.deposit(++transactionIdCounter, amount, account.getCurrency(), account.getId());
    }

    public Transaction withdrawal(Account account, BigDecimal amount) {
        account.withdraw(amount);

        return Transaction.withdrawal(++transactionIdCounter, amount, account.getCurrency(), account.getId());
    }

    public Transaction transfer(Account from, Account to, BigDecimal amount) {
        if (from.equals(to)) {
            throw new IllegalArgumentException("Cannot transfer to same account");
        }

        if (to.getCurrency() != to.getCurrency()) {
            throw new CurrencyMismatchException("Cannot transfer between different currencies");
        }

        from.withdraw(amount);

        try {
            to.deposit(amount);
        } catch (RuntimeException e) {
            from.deposit(amount);
            throw e;
        }

        return Transaction.transfer(++transactionIdCounter, amount, from.getCurrency(), from.getId(), to.getId());
    }
}