package com.majia.concurrentpackage.executors;

import java.util.concurrent.*;

public class FutureExample1 {

    public static void main(String[] args) throws ExecutionException, InterruptedException, TimeoutException {
//        testGet();
        testGetWithTimeOut();
    }

    private static void testGetWithTimeOut() throws InterruptedException, ExecutionException, TimeoutException {
        ThreadPoolExecutor executorService = (ThreadPoolExecutor) Executors.newCachedThreadPool();
        Future<Integer> future = executorService.submit(() -> {
            sleepSeconds(10);
            return 10;
        });
        Integer res = future.get(5, TimeUnit.SECONDS);
        System.out.println(res);
    }

    private static void testGet() throws ExecutionException, InterruptedException {
        ThreadPoolExecutor executorService = (ThreadPoolExecutor) Executors.newCachedThreadPool();
        Future<Integer> future = executorService.submit(() -> {
            sleepSeconds(10);
            return 10;
        });

        System.out.println("=========i will be printed quickly.============");
        Thread callerThread = Thread.currentThread();
        new Thread(() -> {
            sleepSeconds(1);
            callerThread.interrupt();
        }).start();
        Integer res = future.get();
        System.out.println("===========" + res + "==============");
    }

    private static void sleepSeconds(long seconds) {
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
