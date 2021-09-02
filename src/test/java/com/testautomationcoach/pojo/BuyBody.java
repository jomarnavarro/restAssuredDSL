package com.testautomationcoach.pojo;

public class BuyBody extends QuoteBody {

    int qty;

    public BuyBody(String s, int q) {
        super(s);
        this.qty = q;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }
}
