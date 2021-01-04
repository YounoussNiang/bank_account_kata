package org.formation.kata.bank.account.business;

public class Account {
    private final Balance balance;

    public Account(double amount) {
        balance = new Balance(amount);
    }

    public Balance add(double amount){
        return balance.increase(amount);
    }

    public Balance subtract(double amount) {
        return balance.decrease(amount);
    }

    public boolean hasTriggerAnOverDraft(double amount) {
        return balance.isNegativeAfterWithdraw(amount);
    }
}
