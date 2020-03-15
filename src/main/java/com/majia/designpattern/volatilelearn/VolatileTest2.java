package com.majia.designpattern.volatilelearn;

public class VolatileTest2 {

    private static volatile int INIT_VALUE = 0;

    private static final int MAX_LIMIT = 50;

    public static void main(String[] args) {
        new Thread(() -> {
            while (INIT_VALUE < MAX_LIMIT) {
                System.out.printf("T1-> [%d]\n", ++INIT_VALUE);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "ADDER-1").start();

        new Thread(() -> {
            while (INIT_VALUE < MAX_LIMIT) {
                System.out.printf("T2-> [%d]\n", ++INIT_VALUE);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "ADDER-2").start();
    }
}
