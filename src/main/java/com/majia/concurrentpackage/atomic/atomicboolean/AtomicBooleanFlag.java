package com.majia.concurrentpackage.atomic.atomicboolean;

import java.util.concurrent.atomic.AtomicBoolean;

public class AtomicBooleanFlag {

    private static final AtomicBoolean flag = new AtomicBoolean(true);

    public static void main(String[] args) {
        new Thread(()->{
            while (flag.get()){
                try {
                    Thread.sleep(1000);
                    System.out.println("I am working.");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("I am finished.");
        }).start();

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        flag.set(false);
    }
}
