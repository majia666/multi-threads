package com.majia.concurrentpackage.executors;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

public class ExecutorServiceExample1 {

    public static void main(String[] args) throws InterruptedException {
        //isShutDown();
        //isTerminated();
//        executeRunnableError();

        executeRunnableTask();
    }


    private abstract static class MyTask implements Runnable {

        protected final int no;

        public MyTask(int no) {
            this.no = no;
        }

        @Override
        public void run() {
            try {
                this.doInit();
                this.doExecute();
                this.done();
            } catch (Throwable cause) {
                this.error(cause);
            }
        }

        protected abstract void error(Throwable cause);

        protected abstract void done();

        protected abstract void doExecute();

        protected abstract void doInit();
    }

    private static class MyThreadFactory implements ThreadFactory {
        private final AtomicInteger SEQ = new AtomicInteger();

        @Override
        public Thread newThread(Runnable r) {
            Thread thread = new Thread(r);
            thread.setName("My-Thread-" + SEQ.getAndIncrement());
            thread.setUncaughtExceptionHandler((t, cause) -> {
                System.out.println("The Thread " + t.getName() + " execute failed.");
                cause.printStackTrace();
                System.out.println("======================");
            });
            return thread;
        }
    }


    private static void executeRunnableTask() throws InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(10, new MyThreadFactory());

        IntStream.range(0, 10).boxed().forEach(i -> {
            executor.execute(
                    new MyTask(i) {
                        @Override
                        protected void error(Throwable cause) {
                            System.out.println("The no " + i + " failed,update the status ERROR");
                        }

                        @Override
                        protected void done() {
                            System.out.println("The no " + i + " successfully,update the status DONE");
                        }

                        @Override
                        protected void doExecute() {
                            if (i % 3 == 0) {
                                int temp = i / 0;
                            }
                        }

                        @Override
                        protected void doInit() {
                            // do nothing
                        }
                    }
            );
        });
        executor.awaitTermination(10, TimeUnit.SECONDS);
        executor.shutdown();
        System.out.println("===========");
    }

    private static void executeRunnableError() throws InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(10, new MyThreadFactory());

        IntStream.range(0, 10).boxed().forEach(i -> {
            executor.execute(() -> System.out.println(1 / 0));
        });
        executor.awaitTermination(10, TimeUnit.SECONDS);
        executor.shutdown();
        System.out.println("===========");
    }

    private static void isTerminated() {
        ExecutorService executor = Executors.newFixedThreadPool(1);
        executor.execute(() -> {
            sleepSeconds(2);
        });
        executor.shutdown();
        System.out.println(executor.isShutdown());
        System.out.println(executor.isTerminated());
        System.out.println(((ThreadPoolExecutor) executor).isTerminating());
    }

    private static void isShutDown() {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.execute(() -> {
            sleepSeconds(5);
        });
        System.out.println(executor.isShutdown());
        executor.shutdown();
        System.out.println(executor.isShutdown());
        executor.execute(() -> System.out.println("---------------")); //不执行
    }

    private static void sleepSeconds(long seconds) {
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
