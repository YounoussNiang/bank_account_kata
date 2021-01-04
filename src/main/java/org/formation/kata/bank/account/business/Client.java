package org.formation.kata.bank.account.business;

import java.util.Objects;

public class Client {
    private final Account account;

    public Client(Account account) {
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

    public String displayBalanceAccount() {
        return account.displayBalance();
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return Objects.equals(account, client.account);
    }

    @Override
    public int hashCode() {
        return Objects.hash(account);
    }

}
