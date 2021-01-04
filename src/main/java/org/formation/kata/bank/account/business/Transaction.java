package org.formation.kata.bank.account.business;

public class Transaction {

    private final Operation operation;
    private final double value;

    public Transaction(Operation operation, double value) {
        this.operation = operation;
        this.value = value;
    }

    @Override
    public String toString() {
        return  operation +
                ": " + value + " "+ Currency.STERLING;
    }
}
