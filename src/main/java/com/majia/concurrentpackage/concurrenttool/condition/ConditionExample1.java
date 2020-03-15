package com.majia.concurrentpackage.concurrenttool.condition;

import java.sql.Time;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.IntStream;

public class ConditionExample1 {

    private static final ReentrantLock lock = new ReentrantLock();

    private static final Condition condition = lock.newCondition();

    private static int data = 0;

    private static volatile boolean noUse = true;

    public static void main(String[] args) {
        new Thread(() -> {
            for (; ; ) {
                buildData();
            }
        }).start();


        IntStream.range(0,2).forEach(i->{
            new Thread(() -> {
                for (; ; ) {
                    useData();
                }
            }).start();
        });

    }

    private static void buildData() {
        try {
            lock.lock(); // synchronized for word # monitor enter
            while (noUse) {
                condition.await(); // monitor.wait()
            }
            data++;
            Optional.of(Thread.currentThread().getName() + " P: " + data).ifPresent(System.out::println);
            TimeUnit.SECONDS.sleep(1);
            noUse = true;
            condition.signalAll(); // monitor.notify()
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock(); // synchronized end # monitor end
        }
    }

    private static void useData() {
        try {
            lock.lock();
            while (!noUse) {
                condition.await();
            }
            TimeUnit.SECONDS.sleep(1);
            Optional.of(Thread.currentThread().getName() +" C: " + data).ifPresent(System.out::println);
            noUse = false;
            condition.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }


}
