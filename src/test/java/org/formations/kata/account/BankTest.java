package org.formations.kata.account;

import org.formation.kata.bank.account.business.Account;
import org.formation.kata.bank.account.business.Balance;
import org.formation.kata.bank.account.business.Bank;
import org.formation.kata.bank.account.business.Client;
import org.formation.kata.bank.account.exception.InsufficientBalanceException;
import org.formation.kata.bank.account.exception.TooLowAmountException;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class BankTest {

    @Test
    public void shouldNotReturnTheBalanceIfClientDoesntExistInTheBank(){
        Account account = new Account(230.0);
        Client client = new Client(account);
        Bank bank = new Bank(List.of());

        Balance newBalance = bank.deposit(client, 1.0);

        assertNull(newBalance);
    }

    @Test
    public void shouldReturnNewBalanceWhenDeposingNewAmount(){
        Account account = new Account(230.0);
        Client client = new Client(account);
        List<Client> clients = List.of(client);
        Bank bank = new Bank(clients);

        Balance newBalance = bank.deposit(client, 1.0);

        assertEquals(newBalance, new Balance(231.0));
    }

    @Test
    public void shouldThrowAnExceptionWhenAmountToDepositIsUnder1Cent(){
        Account account = new Account(230.0);
        Client client = new Client(account);
        List<Client> clients = List.of(client);
        Bank bank = new Bank(clients);

        assertThrows(TooLowAmountException.class, () -> bank.deposit(client, 0.0));
    }

    @Test
    public void shouldReturnNewBalanceWhenWithdrawingNewAmount(){
        Account account = new Account(230.0);
        Client client = new Client(account);
        List<Client> clients = List.of(client);
        Bank bank = new Bank(clients);

        Balance newBalance = bank.withdraw(client, 50.5);

        assertEquals(newBalance, new Balance(179.5));
    }

    @Test
    public void shouldThrowInsufficientBalanceExceptionWhenAmountToWithdrawIsSuperiorToBalance(){
        Account account = new Account(50.0);
        Client client = new Client(account);
        List<Client> clients = List.of(client);
        Bank bank = new Bank(clients);

        assertThrows(InsufficientBalanceException.class,
                () -> bank.withdraw(client, 100.0));
    }

    @Test
    public void shouldDisplayAccountBalance(){
        Account account = new Account(100.0);
        Client client = new Client(account);
        List<Client> clients = List.of(client);
        Bank bank = new Bank(clients);

        assertEquals("100.OO £", bank.checkBalanceAccount());

    }
}
