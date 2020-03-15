package com.majia.concurrentpackage.concurrenttool.countdown;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchExample3 {

    public static void main(String[] args) {

        CountDownLatch latch = new CountDownLatch(1);

        new Thread(()->{
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            latch.countDown();
        }).start();


        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("===========");

    }
}
