package com.majia.concurrentpackage.concurrenttool.locks;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.StampedLock;
import java.util.stream.Collectors;

public class StampedLockExample2 {

    private static final StampedLock lock = new StampedLock();

    private static final List<Long> DATA = new ArrayList<Long>();

    public static void main(String[] args) {

        ExecutorService executor = Executors.newFixedThreadPool(10);

        Runnable readTask = () -> {
            for (; ; ) {
                read();
            }
        };

        Runnable writeTask = () -> {
            for (; ; ) {
                write();
            }
        };


        executor.submit(readTask);
        executor.submit(readTask);
        executor.submit(readTask);
        executor.submit(readTask);
        executor.submit(readTask);
        executor.submit(readTask);
        executor.submit(writeTask);


    }

    private static void read() {
        long stamp = lock.tryOptimisticRead();

        if (lock.validate(stamp)){
            try {
                stamp = lock.readLock();
                Optional.of(
                        DATA.stream().map(String::valueOf).collect(Collectors.joining("#", "R-", ""))
                ).ifPresent(System.out::println);
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                lock.unlockRead(stamp);
            }
        }
    }


    private static void write() {
        long stamp = -1;
        try {
            stamp = lock.writeLock();
            DATA.add(System.currentTimeMillis());
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlockWrite(stamp);
        }
    }
}
