package com.majia.concurrentpackage.concurrenttool.cyclicbarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

public class CyclicBarrierExample1 {

    public static void main(String[] args) {

        CyclicBarrier barrier = new CyclicBarrier(2, new Runnable() {
            @Override
            public void run() {
                System.out.println("all of finished.");
            }
        });

        new Thread(()->{
            try {
                TimeUnit.SECONDS.sleep(20);
                System.out.println("T1 finished.");
                barrier.await();

                System.out.println("T1 The other thread finished too.");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(()->{
            try {
                TimeUnit.SECONDS.sleep(10);
                System.out.println("T2 finished.");
                barrier.await();
                System.out.println("T2 The other thread finished too.");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
