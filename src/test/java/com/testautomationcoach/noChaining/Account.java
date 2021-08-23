package com.testautomationcoach.noChaining;

public class Account {

    private double balance;

    public Account() {
        this.balance = 0.0;
    }

    public Account(double balance) {
        this.balance = balance;
    }

    public double getBalance() {
        return balance;
    }

    public void withdraw(double withd) {
        this.balance -= withd;
    }

    public void deposit (double balance) {
        this.balance += balance;
    }

    public void printBalance() {
        System.out.println("The balance is " + this.balance);
    }
}
