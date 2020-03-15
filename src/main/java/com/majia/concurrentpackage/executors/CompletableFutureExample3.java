package com.majia.concurrentpackage.executors;


import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class CompletableFutureExample3 {

    public static void main(String[] args) throws InterruptedException, ExecutionException {

        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> "Hello");
//               future.whenComplete((v, t) -> System.out.println("done"));
        future.whenCompleteAsync((v, t) -> {
            System.out.println("=============");
            sleep(5);
        });
        System.out.println(future.get());
        Thread.currentThread().join();

    }


    private static void sleep(long seconds) {
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
