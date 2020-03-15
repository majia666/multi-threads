package com.majia.concurrentpackage.executors;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolExecutorBuild {

    public static void main(String[] args) {

        ThreadPoolExecutor executorService = (ThreadPoolExecutor)buildThreadPoolExecutorBuild();

        int activeCount = -1;
        int queueSize = -1;
        while (true){
            if (executorService.getActiveCount() != activeCount || queueSize != executorService.getQueue().size()){
                System.out.println(executorService.getActiveCount());
                System.out.println(executorService.getCorePoolSize());
                System.out.println(executorService.getQueue().size());
                System.out.println(executorService.getMaximumPoolSize());
                activeCount = executorService.getActiveCount();
                queueSize = executorService.getQueue().size();
            }


        }

    }

    /**
     * int corePoolSize,
     * int maximumPoolSize,
     * long keepAliveTime,
     * TimeUnit unit,
     * BlockingQueue<Runnable> workQueue,
     * ThreadFactory threadFactory,
     * RejectedExecutionHandler handler
     */
    private static ExecutorService buildThreadPoolExecutorBuild(){
        ExecutorService executorService = new ThreadPoolExecutor(1,
                2,
                30,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<Runnable>(1),
                r->{
                    Thread thread = new Thread(r);
                    return thread;
                },
                 new ThreadPoolExecutor.AbortPolicy()
              );
        System.out.println("===The thread pool executor create done.");
        executorService.execute(()->sleepSeconds(100));
        executorService.execute(()->sleepSeconds(10));
        executorService.execute(()->sleepSeconds(100));
//        executorService.execute(()->sleepSeconds(100));
        return executorService;
    }

    private static void sleepSeconds(long seconds){
        try {
            System.out.println("* " + Thread.currentThread().getName() +" *");
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
