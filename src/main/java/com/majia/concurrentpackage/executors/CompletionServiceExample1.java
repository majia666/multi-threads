package com.majia.concurrentpackage.executors;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.*;

public class CompletionServiceExample1 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

//        futureDefect1();
        futureDefect2();
    }

    /**
     * 没有callback
     * @throws ExecutionException
     * @throws InterruptedException
     */
    private static void futureDefect1() throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        Future<Integer> future = executorService.submit(() -> {
            sleepSeconds(100);
            return 10;
        });

        System.out.println("================");

        future.get();
    }
    private static void futureDefect2() throws InterruptedException, ExecutionException {
        ExecutorService executorService = Executors.newFixedThreadPool(2);

        List<Callable<Integer>> callableList = Arrays.asList(
                () -> {
                    sleepSeconds(10);
                    System.out.println("The 10 finished.");
                    return 10;
                },
                () -> {
                    sleepSeconds(20);
                    System.out.println("The 20 finished.");
                    return 20;
                }
                );

        List<Future<Integer>> futures = executorService.invokeAll(callableList);

        Integer v1 = futures.get(0).get();
        System.out.println(v1);
        Integer v2 = futures.get(1).get();
        System.out.println(v2);


    }
    private static void sleepSeconds(long seconds){
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
