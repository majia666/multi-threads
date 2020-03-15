package com.majia.concurrentpackage.executors;


import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class CompletableFutureExample4 {

    public static void main(String[] args) throws InterruptedException, ExecutionException {

//        testThenAcceptBoth();
//        testAcceptEither();
//        testRunAfterBoth();
//        testRunAfterEither();
//        testThenCombine();
        testThenCompose();

        Thread.currentThread().join();

    }
private static void testThenCompose(){
    CompletableFuture.supplyAsync(() -> {
        System.out.println("start the thenCompose-1.");
        sleep(5);
        System.out.println("end the thenCompose-1.");
        return "thenCompose-1";
    }).thenCompose(s->CompletableFuture.supplyAsync(()->{
        System.out.println("start the thenCompose-2.");
        sleep(3);
        System.out.println("end the thenCompose-2.");
        return s.length();
    })).thenAccept(System.out::println);
}
    private static void testThenCombine() {
        CompletableFuture.supplyAsync(() -> {
            System.out.println("start the ThenCombine-1.");
            sleep(5);
            System.out.println("end the ThenCombine-1.");
            return "ThenCombine-1";
        }).thenCombine(CompletableFuture.supplyAsync(() -> {
            System.out.println("start the ThenCombine-2.");
            sleep(3);
            System.out.println("end the ThenCombine-2.");
            return 100;
        }), (v,i) -> v.length()>i).whenComplete((v,i)-> System.out.println(v));
    }

    private static void testRunAfterEither() {
        CompletableFuture.supplyAsync(() -> {
            System.out.println("start the RunAfterEither1.");
            sleep(5);
            System.out.println("end the RunAfterEither1.");
            return "RunAfterEither-1";
        }).runAfterEither(CompletableFuture.supplyAsync(() -> {
            System.out.println("start the RunAfterEither2.");
            sleep(3);
            System.out.println("end the RunAfterEither2.");
            return 100;
        }), () -> System.out.println("DONE"));
    }

    private static void testRunAfterBoth() {
        CompletableFuture.supplyAsync(() -> {
            System.out.println("start the RunAfterBoth1.");
            sleep(5);
            System.out.println("end the RunAfterBoth1.");
            return "RunAfterBoth-1";
        }).runAfterBothAsync(CompletableFuture.supplyAsync(() -> {
            System.out.println("start the RunAfterBoth2.");
            sleep(3);
            System.out.println("end the RunAfterBoth2.");
            return 100;
        }), () -> System.out.println("DONE"));
    }

    private static void testAcceptEither() {
        CompletableFuture.supplyAsync(() -> {
            System.out.println("start the AcceptEither1.");
            sleep(5);
            System.out.println("end the AcceptEither1.");
            return "thenAcceptBoth-1";
        }).acceptEither(CompletableFuture.supplyAsync(() -> {
            System.out.println("start the AcceptEither2.");
            sleep(3);
            System.out.println("end the AcceptEither2.");
            return "thenAcceptBoth-2";
        }), System.out::println);
    }

    private static void testThenAcceptBoth() {
        CompletableFuture.supplyAsync(() -> {
            System.out.println("start the supplyAsync.");
            sleep(5);
            System.out.println("end the supplyAsync.");
            return "thenAcceptBoth";
        }).thenAcceptBoth(CompletableFuture.supplyAsync(() -> {
            System.out.println("start the thenAcceptBoth.");
            sleep(5);
            System.out.println("end the thenAcceptBoth.");
            return 100;
        }), (s, i) -> System.out.println(s + "-----------" + i));
    }

    private static void sleep(long seconds) {
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
