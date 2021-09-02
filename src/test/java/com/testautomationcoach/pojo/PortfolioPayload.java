package com.testautomationcoach.pojo;

import java.util.List;

public class PortfolioPayload {
    double cash;
    double position;
    List<StockInformation> stocks;

    public PortfolioPayload(double cash, double position, List<StockInformation> stocks) {
        this.cash = cash;
        this.position = position;
        this.stocks = stocks;
    }

    public PortfolioPayload() {}

    public double getCash() {
        return cash;
    }

    public void setCash(double cash) {
        this.cash = cash;
    }

    public double getPosition() {
        return position;
    }

    public void setPosition(double position) {
        this.position = position;
    }

    public List<StockInformation> getStocks() {
        return stocks;
    }

    public void setStocks(List<StockInformation> stocks) {
        this.stocks = stocks;
    }

}
