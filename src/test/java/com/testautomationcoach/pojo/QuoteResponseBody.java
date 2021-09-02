package com.testautomationcoach.pojo;

public class QuoteResponseBody {

    String name;
    double price;
    String symbol;

    public QuoteResponseBody(String n, double p, String s) {
        this.name = n;
        this.price = p;
        this.symbol = s;
    }

    public QuoteResponseBody() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }
}
