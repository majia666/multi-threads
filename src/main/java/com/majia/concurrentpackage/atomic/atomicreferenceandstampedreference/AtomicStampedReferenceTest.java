package com.majia.concurrentpackage.atomic.atomicreferenceandstampedreference;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicStampedReference;

public class AtomicStampedReferenceTest {

    private static AtomicStampedReference<Integer> atomic = new AtomicStampedReference<Integer>(100, 0);

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
                boolean success = atomic.compareAndSet(100, 101, atomic.getStamp(), atomic.getStamp() + 1);
                System.out.println(success);
                success = atomic.compareAndSet(101, 100, atomic.getStamp(), atomic.getStamp() + 1);
                System.out.println(success);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread t2 = new Thread(() -> {
            try {

                int stamp = atomic.getStamp();
                System.out.println("Before sleep:stamp= " + stamp);
                TimeUnit.SECONDS.sleep(2);
                boolean success = atomic.compareAndSet(100, 101, stamp, stamp + 1);
                System.out.println(success);
                //                atomic.compareAndSet(101, 100, atomic.getStamp(), atomic.getStamp() + 1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        t1.start();
        t2.start();
        t1.join();
        t2.join();
    }
}
