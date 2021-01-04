package org.formation.kata.bank.account.business;

import org.formation.kata.bank.account.exception.CustomerNotFoundException;
import org.formation.kata.bank.account.exception.InsufficientBalanceException;
import org.formation.kata.bank.account.exception.TooLowAmountException;

import java.util.List;
import java.util.Optional;

public class Bank {

    private final List<Customer> customers;
    private static final double MINIMUM_DEPOSIT_VALUE = 0.01;

    public Bank(List<Customer> customers) {
        this.customers = customers;
    }

    public Balance deposit(Customer currentCustomer, double amount) {
        checkDepositValidity(amount);
        Optional<Customer> optionalClient = findCurrentCustomerInDatabase(currentCustomer);
        if(optionalClient.isPresent()){
            Customer customer = optionalClient.get();
            return customer.deposit(amount);
        }
        throw new CustomerNotFoundException();
    }

    public Balance withdraw(Customer currentCustomer, double amount) {
        checkWithdrawValidity(currentCustomer, amount);
        Optional<Customer> optionalClient = findCurrentCustomerInDatabase(currentCustomer);
        if(optionalClient.isPresent()) {
            Customer customer = optionalClient.get();
            return customer.withdraw(amount);
        }
        throw new CustomerNotFoundException();
    }

    public String checkBalanceAccount(Customer currentCustomer) {
        Optional<Customer> optionalClient = findCurrentCustomerInDatabase(currentCustomer);
        if(optionalClient.isPresent()) {
            Customer customer = optionalClient.get();
            return customer.displayBalanceAccount();
        }
        throw new CustomerNotFoundException();
    }

    private void checkDepositValidity(double amount) {
        if(amount < MINIMUM_DEPOSIT_VALUE)
            throw new TooLowAmountException();
    }

    private void checkWithdrawValidity(Customer customer, double amount){
        if(customer.hasToUseOverDraft(amount))
            throw new InsufficientBalanceException();
    }

    private Optional<Customer> findCurrentCustomerInDatabase(Customer customer){
        return customers.stream()
                .filter(customer::equals)
                .findFirst();
    }
}
