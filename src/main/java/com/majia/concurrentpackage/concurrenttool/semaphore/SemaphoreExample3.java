package com.majia.concurrentpackage.concurrenttool.semaphore;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class SemaphoreExample3 {

    public static void main(String[] args) throws InterruptedException {
        Semaphore semaphore = new Semaphore(1);

        Thread t1 = new Thread(() -> {
            try {
                semaphore.acquire();
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                semaphore.release();
            }

            System.out.println("T1 finished.");
        });

        t1.start();

        TimeUnit.MICROSECONDS.sleep(50);
        Thread t2 = new Thread(() -> {
            try {
                semaphore.acquire();
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                semaphore.release();
            }
            System.out.println("T2 finished.");
        });

        t2.start();

        TimeUnit.MICROSECONDS.sleep(50);
        t2.interrupt();
    }
}
