package com.majia.concurrentpackage.executors;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

public class ExecutorsExample1 {

    public static void main(String[] args) {
//        useCachedThreadPool();
//        useFixedSizePool();

        useSingleThreadPool();

    }

    /**
     * SingleThreadExecutor 和 单线程不同
     *
     *  new FinalizableDelegatedExecutorService(new ThreadPoolExecutor(1, 1,0L, TimeUnit.MILLISECONDS,new LinkedBlockingQueue<Runnable>()));
     */
    private static void useSingleThreadPool(){
        ExecutorService executor = Executors.newSingleThreadExecutor();
        IntStream.range(0, 20).boxed().forEach(i -> {
            executor.execute(() -> {
                sleepSeconds(10);
                System.out.println(Thread.currentThread().getName() + "[" + i + "]");
            });
        });
        sleepSeconds(1);
    }
    /**
     * new ThreadPoolExecutor(nThreads, nThreads,0L, TimeUnit.MILLISECONDS,new LinkedBlockingQueue<Runnable>());
     */
    private static void useFixedSizePool(){
        ExecutorService executor = Executors.newFixedThreadPool(10);

        System.out.println(((ThreadPoolExecutor) executor).getActiveCount());

        IntStream.range(0, 20).boxed().forEach(i -> {
            executor.execute(() -> {
                sleepSeconds(10);
                System.out.println(Thread.currentThread().getName() + "[" + i + "]");
            });
        });
        sleepSeconds(1);
        System.out.println(((ThreadPoolExecutor) executor).getActiveCount());
    }
    /**
     * These pools will typically improve the performance
     * of programs that execute many short-lived asynchronous tasks.
     * <p>
     * return new ThreadPoolExecutor(0, Integer.MAX_VALUE,60L, TimeUnit.SECONDS,new SynchronousQueue<Runnable>());
     * </p>
     */
    private static void useCachedThreadPool() {
        ExecutorService executor = Executors.newCachedThreadPool();

        System.out.println(((ThreadPoolExecutor) executor).getActiveCount());


        executor.execute(() -> {
            System.out.println("==========");
        });

        System.out.println(((ThreadPoolExecutor) executor).getActiveCount());

        IntStream.range(0, 100).boxed().forEach(i -> {
            executor.execute(() -> {
                sleepSeconds(10);
                System.out.println(Thread.currentThread().getName() + "[" + i + "]");
            });
        });
        sleepSeconds(1);
        System.out.println(((ThreadPoolExecutor) executor).getActiveCount());
    }

    private static void sleepSeconds(long seconds) {
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
