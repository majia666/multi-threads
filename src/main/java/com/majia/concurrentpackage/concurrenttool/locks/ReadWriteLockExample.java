package com.majia.concurrentpackage.concurrenttool.locks;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLockExample {

//    private static final Lock lock = new ReentrantLock(true);

    private static final ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    private static final Lock readLock = readWriteLock.readLock();

    private static final Lock writeLock = readWriteLock.writeLock();

    private static final List<Long> data = new ArrayList<Long>();

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> write());
        t1.start();

        TimeUnit.SECONDS.sleep(1);
        Thread t2 = new Thread(() -> read());
        t2.start();

    }

    public static void write() {
        try {
            writeLock.lock();
            data.add(System.currentTimeMillis());

            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            writeLock.unlock();
        }
    }

    public static void read() {
        try {
            readLock.lock();
            data.forEach(System.out::println);
            System.out.println(Thread.currentThread().getName() + "============");
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            readLock.unlock();
        }
    }
}
