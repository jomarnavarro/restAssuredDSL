package com.testautomationcoach.chaining;

public class Person {
    private String name;
    private int id;
    private Account account;

    public Person id(int id) {
        this.id = id;
        return this;
    }

    public Person name(String name) {
        this.name = name;
        return this;
    }

    public Account withAccount(Account account) {
        this.account = account;
        return account;
    }
}
