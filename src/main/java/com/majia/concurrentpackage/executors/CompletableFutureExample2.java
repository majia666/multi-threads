package com.majia.concurrentpackage.executors;


import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class CompletableFutureExample2 {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
//        testSupplyAsync();
//        Future<?> future = testRunAsync();
//        future.get();
        Future<Void> future = testCompletedFuture("Hello");
        System.out.println(future.isDone());

        Thread.currentThread().join();
    }

    private static Future<Void> testCompletedFuture(String data){
        return  CompletableFuture.completedFuture(data).thenAccept(System.out::println);
    }

    private static Future<?> testRunAsync() {
        return CompletableFuture.runAsync(() -> {
            System.out.println("Object=====Start");
            sleep(5);
            System.out.println("Object====End");
        }).whenComplete((v, t) -> System.out.println("================over=========="));
    }

    private static void testSupplyAsync() {
        CompletableFuture.supplyAsync(Object::new).thenAcceptAsync(obj -> {
            System.out.println("Object=====Start " + obj);
            sleep(5);
            System.out.println("Object====End " + obj);
        }).runAfterBoth(CompletableFuture.supplyAsync(() -> "Hello").thenAcceptAsync(s -> {
            System.out.println("Object=====Start " + s);
            sleep(5);
            System.out.println("Object====End " + s);
        }), () -> System.out.println("=====Finished====="));
    }

    private static void sleep(long seconds) {
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
