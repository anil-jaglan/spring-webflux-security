package com.reactive.domain;

import com.reactive.exception.InSufficientFundsException;

public class Wallet {

    private float balance;

    public Wallet() {
        this.balance = 100;
    }

    public float getBalance() {
        return balance;
    }

    public float add(float amount) {
        this.balance += amount;
        return this.balance;
    }

    public float pay(float amount) {
        if (this.balance < amount) {
            throw new InSufficientFundsException("In-sufficent fund in your account.");
        }
        this.balance -= amount;
        return this.balance;
    }

}
