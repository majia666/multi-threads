package com.majia.concurrentpackage.executors;

import java.util.List;
import java.util.concurrent.*;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;

public class ExecutorServiceExample4 {


    public static void main(String[] args) throws ExecutionException, InterruptedException, TimeoutException {

//        testInvokeAny();
//        testInvokeAnyTimeOut();
//        testInvokeAll();
//        testSubmitRunnable();
        testSubmitRunnableWithResult();
    }


    private static void testSubmitRunnableWithResult() throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        String result = "DONE";
        Future<String> future = executorService.submit(() -> {
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, result);
        String s = future.get();
        System.out.println("R: " + s);
    }

    private static void testSubmitRunnable() throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        Future<?> future = executorService.submit(() -> {
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Object o = future.get();
        System.out.println("--------" + o);
    }

    private static void testInvokeAll() throws InterruptedException, ExecutionException {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        executorService.invokeAll(
                IntStream.range(0, 5).boxed().map(i ->
                        (Callable<Integer>) () -> {
                            sleepSeconds();
                            System.out.println(Thread.currentThread().getName() + "-" + i);
                            return i;
                        }
                ).collect(toList())).stream().map(future -> {
            try {
                return future.get();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }).forEach(System.out::println);

        System.out.println("==============finished==========");
    }

    private static void testInvokeAnyTimeOut() throws InterruptedException, ExecutionException, TimeoutException {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        Integer result = executorService.invokeAny(
                IntStream.range(0, 5).boxed().map(i ->
                        (Callable<Integer>) () -> {
                            sleepSeconds();
                            System.out.println(Thread.currentThread().getName() + "-" + i);
                            return i;
                        }
                ).collect(toList()), 1, TimeUnit.SECONDS);

        System.out.println("==============finished==========");
        System.out.println(result);
    }

    private static void testInvokeAny() throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        List<Callable<Integer>> collect = IntStream.range(0, 5).boxed().map(i ->
                (Callable<Integer>) () -> {
                    sleepSeconds();
                    System.out.println(Thread.currentThread().getName() + "-" + i);
                    return i;
                }
        ).collect(toList());
        Integer result = executorService.invokeAny(collect);
        System.out.println("==============finished==========");
        System.out.println(result);
    }

    private static void sleepSeconds() throws InterruptedException {
//        try {
        TimeUnit.SECONDS.sleep(ThreadLocalRandom.current().nextInt(20));
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
    }
}
