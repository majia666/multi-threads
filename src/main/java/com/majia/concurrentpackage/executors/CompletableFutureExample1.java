package com.majia.concurrentpackage.executors;

import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;

public class CompletableFutureExample1 {

    public static void main(String[] args) throws InterruptedException {
//        ExecutorService service = Executors.newFixedThreadPool(10);
//
//        Future<?> future = service.submit(() -> {
//            sleep(10);
//        });
//        while (!future.isDone()){
//
//        }
//        System.out.println("DONE");
//        CompletableFuture.runAsync(() -> {
//            sleep(10);
//        }).whenComplete((v,t)-> System.out.println("DONE"));
//
//        System.out.println("================ i am not blocked.");
//        try {
//            Thread.currentThread().join();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

        // 不能及时返会执行结果
      /*  ExecutorService service = Executors.newFixedThreadPool(10);

        List<Callable<Integer>> tasks = IntStream.range(0, 10).boxed().map(i -> (Callable<Integer>) () -> get()).collect(toList());

        service.invokeAll(tasks).stream().map(future -> {
            try {
                return future.get();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }).parallel().forEach(CompletableFutureExample1::display);*/


        IntStream.range(0, 10).boxed().forEach(i ->
                CompletableFuture.supplyAsync(CompletableFutureExample1::get)
                        .thenAccept(CompletableFutureExample1::display)
                        .whenComplete((v, t) -> System.out.println(i + "DONE.")));

        Thread.currentThread().join();

    }

    private static void display(int data) {
        int value = ThreadLocalRandom.current().nextInt(20);
        try {
            System.out.println(Thread.currentThread().getName() + " display will sleep " + value);
            TimeUnit.SECONDS.sleep(value);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + " display sleep done " + data);
    }

    private static int get() {
        int value = ThreadLocalRandom.current().nextInt(20);
        try {
            System.out.println(Thread.currentThread().getName() + " get will sleep " + value);
            TimeUnit.SECONDS.sleep(value);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + " get sleep done.");
        return value;
    }

    private static void sleep(long seconds) {
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
