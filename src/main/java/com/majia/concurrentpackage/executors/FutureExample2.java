package com.majia.concurrentpackage.executors;

import java.util.concurrent.*;

public class FutureExample2 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
//        testIsDone();
        testCancel();
    }


    private static void testCancel() throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        Future<Integer> future = executorService.submit(() -> {
            sleepSeconds(10);
            return 10;
        });
        System.out.println(future.get());
        System.out.println(future.cancel(true));
        System.out.println(future.cancel(true));
    }
    /**
     * Completion may be due to normal termination, an exception, or
     * cancellation -- in all of these cases, this method will return
     */
    private static void testIsDone() {
        ExecutorService executorService = Executors.newCachedThreadPool();
        Future<Integer> future = executorService.submit(() -> {
            sleepSeconds(1);
            return 10;
        });
        Integer res = null;
        try {
            res = future.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        System.out.println(res + " is done " + future.isDone());

    }

    private static void sleepSeconds(long seconds) {
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
