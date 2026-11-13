package org.minibank.model;

import lombok.Getter;
import org.minibank.exception.InsufficientFundsException;
import org.minibank.exception.WithdrawalLimitExceededException;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

@Getter
public class SavingsAccount extends Account {

    private final BigDecimal interestRate;
    private final int monthlyWithdrawalLimit;
    private int withdrawalsThisMonth;

    public SavingsAccount(String id, String ownerId, Currency currency, LocalDateTime createAt, BigDecimal balance, BigDecimal interestRate, int monthlyWithdrawalLimit) {
        super(id, ownerId, currency, createAt, balance);
        this.interestRate = Objects.requireNonNull(interestRate, "Interest Rate must not be null");
        this.monthlyWithdrawalLimit = monthlyWithdrawalLimit;
    }

    @Override
    public BigDecimal getAvailableBalance() {
        return getBalance();
    }

    @Override
    protected void checkWithdrawalAllowed(BigDecimal amount) {
        if (amount.compareTo(getBalance()) > 0) {
            throw new InsufficientFundsException(amount, getBalance());
        }

        if (withdrawalsThisMonth >= monthlyWithdrawalLimit) {
            throw new WithdrawalLimitExceededException("Monthly withdrawal limit exceeded");
        }
        withdrawalsThisMonth++;
    }

    public void resetMonthlyCounter() {
        withdrawalsThisMonth = 0;
    }
}



