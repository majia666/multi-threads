package com.majia.designpattern.twophasetermination;

public class CounterTest {
    public static void main(String[] args) {
        CounterIncrement counter = new CounterIncrement();
        counter.start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        counter.close();
    }
}
