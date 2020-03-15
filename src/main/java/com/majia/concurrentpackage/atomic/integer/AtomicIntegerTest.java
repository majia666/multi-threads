package com.majia.concurrentpackage.atomic.integer;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

public class AtomicIntegerTest {

    /**
     * 1.保证内存可见性
     * 2.顺序性（内存屏障）
     * 3.不能保证原子性
     */
    private static volatile int value = 0;

    private static Set<Integer> set = Collections.synchronizedSet(new HashSet<Integer>());

    public static void main(String[] args) throws InterruptedException {
     /*   Thread t1 = new Thread() {
            @Override
            public void run() {
                int x = 0;
                while (x < 500) {
                    set.add(value);
                    int temp = value;
                    System.out.println(Thread.currentThread().getName() + ":" + temp);
                    value += 1;
                    x++;
                }
            }
        };

        Thread t2 = new Thread() {
            @Override
            public void run() {
                int x = 0;
                while (x < 500) {
                    set.add(value);
                    int temp = value;
                    System.out.println(Thread.currentThread().getName() + ":" + temp);
                    value += 1;
                    x++;
                }
            }
        };

        Thread t3 = new Thread() {
            @Override
            public void run() {
                int x = 0;
                while (x < 500) {
                    set.add(value);
                    int temp = value;
                    System.out.println(Thread.currentThread().getName() + ":" + temp);
                    value += 1;
                    x++;
                }
            }
        };

        t1.start();
        t2.start();
        t3.start();
        t1.join();
        t2.join();
        t3.join();

        System.out.println(set.size());*/

        AtomicInteger v = new AtomicInteger();
        Thread t1 = new Thread() {
            @Override
            public void run() {
                int x = 0;
                while (x < 500) {
                    int i = v.getAndIncrement();
                    set.add(i);

                    System.out.println(Thread.currentThread().getName() + ":" + i);
                    i += 1;
                    x++;
                }
            }
        };

        Thread t2 = new Thread() {
            @Override
            public void run() {
                int x = 0;
                while (x < 500) {
                    int i = v.getAndIncrement();
                    set.add(i);

                    System.out.println(Thread.currentThread().getName() + ":" + i);
                    i += 1;
                    x++;
                }
            }
        };
        Thread t3 = new Thread() {
            @Override
            public void run() {
                int x = 0;
                while (x < 500) {
                    int i = v.getAndIncrement();
                    set.add(i);

                    System.out.println(Thread.currentThread().getName() + ":" + i);
                    i += 1;
                    x++;
                }
            }
        };

        t1.start();
        t2.start();
        t3.start();
        t1.join();
        t2.join();
        t3.join();
        System.out.println(set.size());
    }
}
