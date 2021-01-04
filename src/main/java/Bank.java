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

    private void checkDepositValidity(double amount) {
        double MINIMUM_DEPOSIT_VALUE = 0.01;
        if(amount < MINIMUM_DEPOSIT_VALUE)
            throw new TooLowAmountException();
    }
}
