package org.minibank.exception;

public class AccountNotFoundException extends BankException {

    private final String accountId;

    public AccountNotFoundException(String message, String accountId) {
        super("Account not found" + accountId);
        this.accountId = accountId;
    }

    public String getAccountId() {
        return accountId;
    }
}
