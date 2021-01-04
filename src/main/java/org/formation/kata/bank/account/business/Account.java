package org.formation.kata.bank.account.business;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Account {
    private final Balance balance;
    private final List<Transaction> transactionHistory = new ArrayList<>();

    public Account(double amount) {
        balance = new Balance(amount);
    }

    public Balance add(double amount){
        transactionHistory.add(new Transaction(Operation.DEPOSIT,amount));
        return balance.increase(amount);
    }

    public Balance subtract(double amount) {
        transactionHistory.add(new Transaction(Operation.WITHDRAW,amount));
        return balance.decrease(amount);
    }

    public boolean hasTriggerAnOverDraft(double amount) {
        return balance.isNegativeAfterWithdraw(amount);
    }

    public String checkBalance() {
        return balance.toString();
    }

    public String generateTransactionHistory() {
        String history = transactionHistory.stream()
                .map(transaction -> transaction.toString() + "\n")
                .collect(Collectors.joining());
        return history.trim();
    }
}
