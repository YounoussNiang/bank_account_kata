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
}
