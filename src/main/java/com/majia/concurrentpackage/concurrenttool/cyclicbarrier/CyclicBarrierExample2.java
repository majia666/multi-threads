package com.majia.concurrentpackage.concurrenttool.cyclicbarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

public class CyclicBarrierExample2 {

    public static void main(String[] args) {

        CyclicBarrier barrier = new CyclicBarrier(1);

        new Thread(()->{
            try {
                TimeUnit.SECONDS.sleep(5);
                try {
                    barrier.await();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();


    }
}
