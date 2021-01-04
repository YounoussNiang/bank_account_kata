public class Account {
    private final Balance balance;

    public Account(double amount) {
        balance = new Balance(amount);
    }

    public Balance add(double amount){
        return balance.increment(amount);
    }
}
