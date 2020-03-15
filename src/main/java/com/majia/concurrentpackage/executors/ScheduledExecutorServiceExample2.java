package com.majia.concurrentpackage.executors;

import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

public class ScheduledExecutorServiceExample2 {

    public static void main(String[] args) {

//       testScheduleWithFixedDelay();
//        testGetContinueExistingPeriodicTasksAfterShutdownPolicy();
        testGetExecuteExistingDelayedTasksAfterShutdownPolicy();

    }

    private static void testScheduleWithFixedDelay(){
        ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(2);
//        ScheduledFuture<?> future = executor.scheduleWithFixedDelay(() ->
//                System.out.println("====" + System.currentTimeMillis()), 1, 2, TimeUnit.SECONDS);
        AtomicLong interval = new AtomicLong(0L);
        ScheduledFuture<?> future = executor.scheduleWithFixedDelay(() -> {
            long currentTimeMillis = System.currentTimeMillis();
            if (interval.get() == 0) {
                System.out.printf("The first time trigger task at %d\n", currentTimeMillis);
            }else {
                System.out.printf("The actually spend %d\n", currentTimeMillis -interval.get());
            }
            interval.set(currentTimeMillis);
            System.out.println(Thread.currentThread().getName());
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }, 1, 2, TimeUnit.SECONDS);
    }

    private static void testGetExecuteExistingDelayedTasksAfterShutdownPolicy(){
        ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(2);
        System.out.println(executor.getExecuteExistingDelayedTasksAfterShutdownPolicy());
        executor.setExecuteExistingDelayedTasksAfterShutdownPolicy(false);
        AtomicLong interval = new AtomicLong(0L);
        ScheduledFuture<?> future = executor.scheduleWithFixedDelay(() -> {
            long currentTimeMillis = System.currentTimeMillis();
            if (interval.get() == 0) {
                System.out.printf("The first time trigger task at %d\n", currentTimeMillis);
            }else {
                System.out.printf("The actually spend %d\n", currentTimeMillis -interval.get());
            }
            interval.set(currentTimeMillis);
            System.out.println(Thread.currentThread().getName());
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }, 1, 2, TimeUnit.SECONDS);
        sleepSeconds(2);
        executor.shutdown();
        System.out.println("==over===");
    }

    private static void testGetContinueExistingPeriodicTasksAfterShutdownPolicy(){
        ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(2);

        System.out.println(executor.getContinueExistingPeriodicTasksAfterShutdownPolicy());

        executor.setContinueExistingPeriodicTasksAfterShutdownPolicy(true);
        AtomicLong interval = new AtomicLong(0L);
        ScheduledFuture<?> future = executor.scheduleAtFixedRate(() -> {
            long currentTimeMillis = System.currentTimeMillis();
            if (interval.get() == 0) {
                System.out.printf("The first time trigger task at %d\n", currentTimeMillis);
            }else {
                System.out.printf("The actually spend %d\n", currentTimeMillis -interval.get());
            }
            interval.set(currentTimeMillis);
            System.out.println(Thread.currentThread().getName());
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }, 1, 2, TimeUnit.SECONDS);
        sleepSeconds(2);

        executor.shutdown();
        System.out.println("===over====");
    }


    private static void sleepSeconds(long seconds){
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
