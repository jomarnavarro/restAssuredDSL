package com.testautomationcoach.chaining;

public class Account {
    public double balance;

    public Account() {
        this.balance = 0.0;
    }

    public Account(double balance) {
        this.balance = balance;
    }

    public Account withdraw(double amount) {
        this.balance = this.balance - amount;
        return this;
    }

    public double getBalance() {
        return this.balance;
    }

    public Account andThen() {
        return this;
    }

    public Account printBalance() {
        System.out.println("The balance is " + this.balance);
        return this;
    }

    public Account withBalance(double balance) {
        this.balance = balance;
        return this;
    }

    public Account deposit(double balance) {
        this.balance += balance;
        return this;
    }
}
