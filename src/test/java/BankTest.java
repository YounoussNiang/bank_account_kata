import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BankTest {

    @Test
    public void depositAmount(){
        Account account = new Account(230.0);
        Client client = new Client(account);
        List<Client> clients = List.of(client);
        Bank bank = new Bank(clients);

        Balance newBalance = bank.deposit(client, 1.0);

        assertEquals(newBalance, new Balance(231.0));
    }
}
