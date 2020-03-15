package com.majia.concurrentpackage.concurrenttool.cyclicbarrier;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class CyclicBarrierExample3 {

    static class MyCountDownLatch extends CountDownLatch {

        private final Runnable runnable;

        /**
         * Constructs a {@code CountDownLatch} initialized with the given count.
         *
         * @param count    the number of times {@link #countDown} must be invoked
         *                 before threads can pass through {@link #await}
         * @param runnable
         * @throws IllegalArgumentException if {@code count} is negative
         */
        public MyCountDownLatch(int count, Runnable runnable) {
            super(count);
            this.runnable = runnable;
        }

        public void countDown() {
            super.countDown();
            if (getCount() == 0) {
                this.runnable.run();
            }
        }
    }

    public static void main(String[] args) {

        MyCountDownLatch downLatch = new MyCountDownLatch(2, new Runnable() {
            @Override
            public void run() {
                System.out.println("all of work done finish.");
            }
        });

        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " finished.");
            downLatch.countDown();
        }).start();

        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " finished.");
            downLatch.countDown();
        }).start();
    }
}
