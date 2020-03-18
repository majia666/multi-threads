package com.majia.test.testimmutable.exer;

public class Synch {

    private final String name = "Synch";

    @Override
    public synchronized String toString() {
        return "Synch{" +
                "name='" + name + '\'' +
                '}';
    }
}
