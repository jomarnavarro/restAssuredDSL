package com.testautomationcoach.test;

import javafx.util.Pair;

import org.testng.annotations.Test;

public class PairingStuff {

    @Test
    public void testPairs() {
        Pair p = new Pair<String, String>("Hello", "World");
        System.out.println(String.format(
                "Key: %s, Value: %s", p.getKey(), p.getValue()
        ));
    }
}
