package org.minibank;

import org.minibank.model.Currency;
import org.minibank.model.Transaction;

import java.math.BigDecimal;

public class Main {
    public static void main(String[] args) {
        Transaction transaction = Transaction.deposit(1, new BigDecimal("1000.00"), Currency.USD, "HamkorBank");
        System.out.println(transaction);
    }

}
