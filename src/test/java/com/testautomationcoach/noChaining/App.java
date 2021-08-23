package com.testautomationcoach.noChaining;

public class App {
    public static void main(String[] args) {
        Person p = new Person();

        p.setId(1);
        p.setName("Jorge");
        p.setAccount(new Account(10.0));

        Account a = p.getAccount();
        a.printBalance();
        a.withdraw(5.0);
        a.printBalance();
        a.deposit(15.0);
        a.printBalance();

        String name = p.getName();
        double balance = a.getBalance();

        System.out.println(name + " has an account with balance $" + balance);
    }
}
