package com.majia.concurrentpackage.executors;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

public class ExecutorServiceExample2 {

    public static void main(String[] args) throws InterruptedException {
//        testAbortPolicy();
//        testDiscardPolicy();
//        testCallerRunsPolicy();
        testDiscardOldestPolicy();
    }


    private static void testDiscardOldestPolicy() {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                1,
                2,
                30,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(1),
                r -> {
                    Thread thread = new Thread(r);
                    return thread;
                },
                new ThreadPoolExecutor.DiscardOldestPolicy());

        IntStream.range(0, 3).forEach(i -> {
            executor.execute(() -> {
                sleepSeconds(5);
                System.out.println("I am come lambda.");
            });
        });

        sleepSeconds(1);

        executor.execute(() -> {
                    System.out.println("xdfdfd");
                    System.out.println(Thread.currentThread().getName());
                }
        );

        System.out.println("=======+=================");
    }


    private static void testCallerRunsPolicy() {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                1,
                2,
                30,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(1),
                r -> {
                    Thread thread = new Thread(r);
                    return thread;
                },
                new ThreadPoolExecutor.CallerRunsPolicy());

        IntStream.range(0, 3).forEach(i -> {
            executor.execute(() -> sleepSeconds(100));
        });

        sleepSeconds(1);

        executor.execute(() -> {
                    System.out.println("xdfdfd");
                    System.out.println(Thread.currentThread().getName());
                }
        );
    }

    private static void testDiscardPolicy() {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                1,
                2,
                30,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(1),
                r -> {
                    Thread thread = new Thread(r);
                    return thread;
                },
                new ThreadPoolExecutor.DiscardOldestPolicy());

        IntStream.range(0, 3).forEach(i -> {
            executor.execute(() -> sleepSeconds(100));
        });

        sleepSeconds(1);

        executor.execute(() -> System.out.println("====="));

        System.out.println("0----------------99900");
    }

    private static void testAbortPolicy() {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                1,
                2,
                30,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(1),
                r -> {
                    Thread thread = new Thread(r);
                    return thread;
                },
                new ThreadPoolExecutor.AbortPolicy());

        IntStream.range(0, 3).forEach(i -> {
            executor.execute(() -> sleepSeconds(100));
        });

        sleepSeconds(1);

        executor.execute(() -> System.out.println("====="));
    }

    private static void sleepSeconds(long seconds) {
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
