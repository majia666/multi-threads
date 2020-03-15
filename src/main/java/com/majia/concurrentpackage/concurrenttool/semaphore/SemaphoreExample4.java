package com.majia.concurrentpackage.concurrenttool.semaphore;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class SemaphoreExample4 {

    public static void main(String[] args) throws InterruptedException {
        Semaphore semaphore = new Semaphore(5);

        Thread t1 = new Thread(() -> {
            try {
                semaphore.drainPermits();
//                semaphore.acquire();
                System.out.println(semaphore.availablePermits());
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                semaphore.release(5);
            }

            System.out.println("T1 finished.");
        });

        t1.start();

        TimeUnit.SECONDS.sleep(1);

        Thread t2 = new Thread(() -> {
            try {
                semaphore.drainPermits();
//                semaphore.acquire();
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                semaphore.release(5);
            }

            System.out.println("T2 finished.");
        });

        t2.start();

        TimeUnit.SECONDS.sleep(1);
        System.out.println(semaphore.hasQueuedThreads());
    }
}
