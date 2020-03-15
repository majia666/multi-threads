package com.majia.concurrentpackage.executors;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

public class CompletionServiceExample2 {

    public static void main(String[] args) throws InterruptedException, ExecutionException {

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

        ExecutorCompletionService<Integer> completionService = new ExecutorCompletionService<>(executorService);

        List<Future<Integer>> futures = new ArrayList<Future<Integer>>();

        callableList.stream().forEach(callable->futures.add(completionService.submit(callable)));

//        Future<Integer> future = null;
//        while (null != (future=completionService.take())){
//            System.out.println(future.get());
//        }
        System.out.println(completionService.poll(11,TimeUnit.SECONDS).get());


    }


    private static void sleepSeconds(long seconds){
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
