import java.util.List;
import java.util.Optional;

public class Bank {
    private List<Client> clients;

    public Bank(List<Client> clients) {
        this.clients = clients;
    }

    public Balance deposit(Client currentClient, double amount) {
        Balance newBalance = null;
        Optional<Client> optionalClient = clients.stream()
                .filter(client1 -> currentClient.equals(client1))
                .findFirst();
        if(optionalClient.isPresent()){
            Client client = optionalClient.get();
            newBalance = client.deposit(amount);
        }

        return newBalance;
    }
}
