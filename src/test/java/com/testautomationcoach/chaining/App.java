package com.testautomationcoach.chaining;

public class App {
    public static void main(String[] args) {
        new Person()
                .id(1)
                .name("John")
                .withAccount(new Account(10.0))
                .withdraw(5.0)
                .printBalance();

        System.out.println("##############");
        new Account()
                .withBalance(10.0)
                .andThen()
                .printBalance()
                .andThen()
                .withdraw(5.0)
                .andThen()
                .printBalance()
                .andThen()
                .deposit(15.0)
                .andThen()
                .printBalance();
    }
}
