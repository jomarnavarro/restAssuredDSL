package com.testautomationcoach.pojo;

public class BuyResponseBody {
    public BuyResponseBody(String message) {
        this.message = message;
    }

    public BuyResponseBody() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    String message;
}
