package com.majia.concurrentpackage.executors;


import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class CompletableFutureExample5 {

    public static void main(String[] args) throws InterruptedException, ExecutionException {

//        testGetNow();
//        testComplete();
        testJoin();
        Thread.currentThread().join();

    }

    private static void testJoin() {
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            sleep(5);
            System.out.println("-===================9090-");
            return "HELLO";
        });

        String join = future.join();
        System.out.println(join);
    }

    private static void testComplete() throws ExecutionException, InterruptedException {
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            sleep(5);
            System.out.println("-===================9090-");
            return "HELLO";
        });
        sleep(1);
        boolean finished = future.complete("WORLD");

        System.out.println(finished);
        System.out.println(future.get());

    }

    private static void testGetNow() throws ExecutionException, InterruptedException {
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            sleep(5);
            return "HELLO";
        });

        String result = future.getNow("WOELD");
        System.out.println(result);
        System.out.println(future.get());
    }

    private static void sleep(long seconds) {
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
