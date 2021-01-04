package org.formation.kata.bank.account.business;

public class Transaction {

    private final Operation operation;
    private final double amount;

    public Transaction(Operation operation, double amount) {
        this.operation = operation;
        this.amount = amount;
    }

    @Override
    public String toString() {
        return  operation +
                ": " + amount + " "+ Currency.STERLING;
    }
}
