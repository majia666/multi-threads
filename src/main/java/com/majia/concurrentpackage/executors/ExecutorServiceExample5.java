package com.majia.concurrentpackage.executors;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class ExecutorServiceExample5 {

    public static void main(String[] args) {
        ThreadPoolExecutor executorService = (ThreadPoolExecutor) Executors.newFixedThreadPool(5);
        executorService.execute(()->{
            System.out.println("I will be process");
        });
        executorService.getQueue().add(()->{
            System.out.println("I am added.");
        });
    }
}
