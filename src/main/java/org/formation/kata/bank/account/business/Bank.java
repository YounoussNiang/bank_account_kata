package org.formation.kata.bank.account.business;

import org.formation.kata.bank.account.exception.InsufficientBalanceException;
import org.formation.kata.bank.account.exception.TooLowAmountException;

import java.util.List;
import java.util.Optional;

public class Bank {
    private final List<Customer> customers;

    public Bank(List<Customer> customers) {
        this.customers = customers;
    }

    public Balance deposit(Customer currentCustomer, double amount) {
        checkDepositValidity(amount);
        Balance newBalance = null;
        Optional<Customer> optionalClient = customers.stream()
                .filter(currentCustomer::equals)
                .findFirst();
        if(optionalClient.isPresent()){
            Customer customer = optionalClient.get();
            newBalance = customer.deposit(amount);
        }

        return newBalance;
    }

    public Balance withdraw(Customer currentCustomer, double amount) {
        checkWithdrawValidity(currentCustomer, amount);
        Balance newBalance = null;
        Optional<Customer> optionalClient = customers.stream()
                .filter(currentCustomer::equals)
                .findFirst();
        if(optionalClient.isPresent()) {
            Customer customer = optionalClient.get();
            newBalance = customer.withdraw(amount);
        }
        return newBalance;
    }

    private void checkDepositValidity(double amount) {
        double MINIMUM_DEPOSIT_VALUE = 0.01;
        if(amount < MINIMUM_DEPOSIT_VALUE)
            throw new TooLowAmountException();
    }

    private void checkWithdrawValidity(Customer customer, double amount){
        if(customer.hasToUseOverDraft(amount))
            throw new InsufficientBalanceException();
    }

    public String checkBalanceAccount(Customer currentCustomer) {
        String stringifyBalance = null;
        Optional<Customer> optionalClient = customers.stream()
                .filter(currentCustomer::equals)
                .findFirst();
        if(optionalClient.isPresent()) {
            Customer customer = optionalClient.get();
            stringifyBalance = customer.displayBalanceAccount();
        }
        return stringifyBalance;
    }
}
