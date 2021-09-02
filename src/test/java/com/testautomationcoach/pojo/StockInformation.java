package com.testautomationcoach.pojo;

public class StockInformation {
    String name;
    int ns;
    double partial_total;
    double price;
    String symbol;

    public StockInformation(String name, int ns, double pt, double price, String symbol) {
        this.name = name;
        this.ns = ns;
        this.partial_total = pt;
        this.price = price;
        this.symbol = symbol;
    }

    public StockInformation() {}
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNs() {
        return ns;
    }

    public void setNs(int ns) {
        this.ns = ns;
    }

    public double getPartial_total() {
        return partial_total;
    }

    public void setPartial_total(double partial_total) {
        this.partial_total = partial_total;
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
