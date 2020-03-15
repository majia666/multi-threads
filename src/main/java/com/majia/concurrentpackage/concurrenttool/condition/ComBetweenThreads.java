package com.majia.concurrentpackage.concurrenttool.condition;

import java.util.concurrent.TimeUnit;

public class ComBetweenThreads {

    private static int data = 0;

    private static volatile boolean noUse = true;

    private static final Object MONITOR = new Object();

    public static void main(String[] args) {
        new Thread(() -> {
            for (; ; ) {
                buildData();
            }
        }).start();

        new Thread(() -> {
            for (; ; ) {
                useData();
            }
        }).start();
    }

    private static void sleep(long seconds) {
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void buildData() {
        synchronized (MONITOR) {
            while (noUse) {
                try {
                    MONITOR.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            data++;
            sleep(1);
            System.out.println(Thread.currentThread().getName() + "-P: " + data);
            noUse = true;
            MONITOR.notifyAll();
        }
    }

    public static void useData() {
        synchronized (MONITOR) {
            while (!noUse) {
                try {
                    MONITOR.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            sleep(1);
            System.out.println(Thread.currentThread().getName() + "-C: " + data);
            noUse = false;
            MONITOR.notifyAll();
        }
    }
}
