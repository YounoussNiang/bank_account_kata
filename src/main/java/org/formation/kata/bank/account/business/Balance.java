package org.formation.kata.bank.account.business;

import java.util.Objects;

public class Balance {

    private final double value;

    public Balance(double value) {
        this.value = value;
    }

    public Balance increase(double amount){
        return new Balance(value + amount);
    }

    public Balance decrease(double amount) {
        return new Balance(value - amount);
    }

    public boolean isNegativeAfterWithdraw(double amount) {
        return (value - amount < 0);
    }

    @Override
    public String toString() {
        return value+" £";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Balance balance = (Balance) o;
        return Double.compare(balance.value, value) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

}
