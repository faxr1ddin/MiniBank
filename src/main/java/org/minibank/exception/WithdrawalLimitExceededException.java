package org.minibank.exception;

public class WithdrawalLimitExceededException extends BankException {

    public WithdrawalLimitExceededException(String message) {
        super(message);
    }
}
