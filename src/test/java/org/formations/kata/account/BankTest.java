package org.formations.kata.account;

import org.formation.kata.bank.account.business.Account;
import org.formation.kata.bank.account.business.Balance;
import org.formation.kata.bank.account.business.Bank;
import org.formation.kata.bank.account.business.Customer;
import org.formation.kata.bank.account.exception.CustomerNotFoundException;
import org.formation.kata.bank.account.exception.InsufficientBalanceException;
import org.formation.kata.bank.account.exception.TooLowAmountException;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class BankTest {

    @Nested
    class Deposit{
        @Test
        public void shouldThrowCustomerNotFoundExceptionWhenCustomerDoesntExist(){
            Account account = new Account(230.0);
            Customer customer = new Customer(account);
            Bank bank = new Bank(List.of());

            assertThrows(CustomerNotFoundException.class, () -> bank.deposit(customer, 1.0));
        }

        @Test
        public void shouldReturnNewBalance(){
            Account account = new Account(230.0);
            Customer customer = new Customer(account);
            List<Customer> customers = List.of(customer);
            Bank bank = new Bank(customers);

            Balance newBalance = bank.deposit(customer, 1.0);

            assertEquals(newBalance, new Balance(231.0));
        }

        @Test
        public void shouldThrowAnExceptionWhenAmountIsUnder1Cent(){
            Account account = new Account(230.0);
            Customer customer = new Customer(account);
            List<Customer> customers = List.of(customer);
            Bank bank = new Bank(customers);

            assertThrows(TooLowAmountException.class, () -> bank.deposit(customer, 0.0));
        }

    }

    @Nested
    class Withdraw {
        @Test
        public void shouldThrowCustomerNotFoundExceptionWhenCustomerDoesntExist() {
            Account account = new Account(230.0);
            Customer customer = new Customer(account);
            Bank bank = new Bank(List.of());

            assertThrows(CustomerNotFoundException.class,
                    () -> bank.withdraw(customer, 1.0));
        }

        @Test
        public void shouldReturnNewBalance(){
            Account account = new Account(230.0);
            Customer customer = new Customer(account);
            List<Customer> customers = List.of(customer);
            Bank bank = new Bank(customers);

            Balance newBalance = bank.withdraw(customer, 50.5);

            assertEquals(newBalance, new Balance(179.5));
        }

        @Test
        public void shouldThrowInsufficientBalanceExceptionWhenAmountIsSuperiorToBalance(){
            Account account = new Account(50.0);
            Customer customer = new Customer(account);
            List<Customer> customers = List.of(customer);
            Bank bank = new Bank(customers);

            assertThrows(InsufficientBalanceException.class,
                    () -> bank.withdraw(customer, 100.0));
        }
    }

    @Nested
    class Format {

        @Test
        public void shouldThrowCustomerNotFoundExceptionWhenCustomerDoesntExist() {
            Account account = new Account(230.0);
            Customer customer = new Customer(account);
            Bank bank = new Bank(List.of());

            assertThrows(CustomerNotFoundException.class,
                    () -> bank.displayBalanceAccount(customer));
        }

        @Test
        public void shouldThrowCustomerNotFoundExceptionWhenNonExistentCustomerCheckTransactionHistory() {
            Account account = new Account(230.0);
            Customer customer = new Customer(account);
            Bank bank = new Bank(List.of());

            assertThrows(CustomerNotFoundException.class,
                    () -> bank.displayTransactionHistory(customer));
        }


        @Test
        public void shouldDisplayAccountBalance() {
            Account account = new Account(120.45);
            Customer customer = new Customer(account);
            List<Customer> customers = List.of(customer);
            Bank bank = new Bank(customers);

            assertEquals("120.45 £", bank.displayBalanceAccount(customer));
        }


        @Test
        public void shouldDisplayTransactionHistory() {
            Account account = new Account(50.0);
            Customer customer = new Customer(account);
            List<Customer> customers = List.of(customer);
            Bank bank = new Bank(customers);

            bank.deposit(customer, 100);
            bank.withdraw(customer, 20);
            bank.displayBalanceAccount(customer);

            assertEquals("DEPOSIT: 100.0 £\nWITHDRAW: 20.0 £", bank.displayTransactionHistory(customer));
        }
    }
}
