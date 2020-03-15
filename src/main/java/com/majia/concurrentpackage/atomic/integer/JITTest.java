package com.majia.concurrentpackage.atomic.integer;

public class JITTest {

    private static volatile boolean init = false;

    public static void main(String[] args) throws InterruptedException {
        new Thread(() -> {
            while (!init) {
                //System.out.println(".");
            }
        }).start();

        Thread.sleep(1000);
        new Thread(() -> {
            init = true;
            System.out.println("Set init to true.");
        }).start();
    }
}
