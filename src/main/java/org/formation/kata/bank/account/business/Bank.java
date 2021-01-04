package org.formation.kata.bank.account.business;

import org.formation.kata.bank.account.exception.InsufficientBalanceException;
import org.formation.kata.bank.account.exception.TooLowAmountException;

import java.util.List;
import java.util.Optional;

public class Bank {
    private final List<Client> clients;

    public Bank(List<Client> clients) {
        this.clients = clients;
    }

    public Balance deposit(Client currentClient, double amount) {
        checkDepositValidity(amount);
        Balance newBalance = null;
        Optional<Client> optionalClient = clients.stream()
                .filter(currentClient::equals)
                .findFirst();
        if(optionalClient.isPresent()){
            Client client = optionalClient.get();
            newBalance = client.deposit(amount);
        }

        return newBalance;
    }

    public Balance withdraw(Client currentClient, double amount) {
        checkWithdrawValidity(currentClient, amount);
        Balance newBalance = null;
        Optional<Client> optionalClient = clients.stream()
                .filter(currentClient::equals)
                .findFirst();
        if(optionalClient.isPresent()) {
            Client client = optionalClient.get();
            newBalance = client.withdraw(amount);
        }
        return newBalance;
    }

    private void checkDepositValidity(double amount) {
        double MINIMUM_DEPOSIT_VALUE = 0.01;
        if(amount < MINIMUM_DEPOSIT_VALUE)
            throw new TooLowAmountException();
    }

    private void checkWithdrawValidity(Client client, double amount){
        if(client.hasToUseOverDraft(amount))
            throw new InsufficientBalanceException();
    }

    public String checkBalanceAccount(Client currentClient) {
        String stringifyBalance = null;
        Optional<Client> optionalClient = clients.stream()
                .filter(currentClient::equals)
                .findFirst();
        if(optionalClient.isPresent()) {
            Client client = optionalClient.get();
            stringifyBalance = client.displayBalanceAccount();
        }
        return stringifyBalance;
        //"100.OO £";
    }
}
