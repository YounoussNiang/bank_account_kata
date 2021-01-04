package org.formation.kata.bank.account.business;

import java.util.Objects;

public class Customer {

    private final Account account;

    public Customer(Account account) {
        this.account = account;
    }

    public boolean hasToUseOverDraft(double amount){
        return account.hasTriggerAnOverDraft(amount);
    }

    public Balance deposit(double amount){
        return account.add(amount);
    }

    public Balance withdraw(double amount) {
        return account.subtract(amount);
    }

    public String checkBalanceAccount() {
        return account.checkBalance();
    }

    public String displayAccountTransactionHistory() {
        return account.generateTransactionHistory();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return Objects.equals(account, customer.account);
    }

    @Override
    public int hashCode() {
        return Objects.hash(account);
    }

}
