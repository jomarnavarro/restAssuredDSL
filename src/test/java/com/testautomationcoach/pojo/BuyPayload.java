package com.testautomationcoach.pojo;

public class BuyPayload {
    String symbol;
    int qty;

    public BuyPayload(String s, int q) {
        this.symbol = s;
        this.qty = q;
    }

    public BuyPayload() {}

    //... getters and setters...


    public String getSymbol() {
        return this.symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public int getQty() {
        return this.qty;
    }

    public void setQty(int q) {
        this.qty = q;
    }
}
