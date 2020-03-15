package com.majia.concurrentpackage.executors;

import java.util.concurrent.*;
import java.util.stream.IntStream;

public class ExecutorServiceExample3 {

    public static void main(String[] args) {
//        test();
//        testAllowCoreThreadTimeOut();
//        testRemove();
//        testPrestartCoreThread();
//        testPrestartAllCoreThreads();

        testThreadPoolAdvice();
    }



    private static void testPrestartAllCoreThreads() {
        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(2);
        executor.setMaximumPoolSize(3);
        System.out.println(executor.getActiveCount());
        System.out.println(executor.prestartAllCoreThreads());
        System.out.println(executor.getActiveCount());
    }

    private static void testPrestartCoreThread() {
        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(2);
        System.out.println(executor.getActiveCount());

        System.out.println(executor.prestartCoreThread());
        System.out.println(executor.getActiveCount());

        System.out.println(executor.prestartCoreThread());
        System.out.println(executor.getActiveCount());
        IntStream.range(0, 2).boxed().forEach(i -> {
            executor.execute(() -> {
                sleepSeconds(1);
            });
        });
        System.out.println(executor.prestartCoreThread());
        System.out.println(executor.getActiveCount());

    }

    private static void testRemove() {
        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(2);

        executor.setKeepAliveTime(10, TimeUnit.SECONDS);
        executor.allowCoreThreadTimeOut(true);

        IntStream.range(0, 2).boxed().forEach(i -> {
            executor.execute(() -> {
                sleepSeconds(3);
                System.out.println("=========== i am finished.");
            });
        });
        sleepMilliSeconds(20);
        Runnable r = () -> {
            System.out.println("i am never be executed.");
        };
        executor.execute(r);
        sleepMilliSeconds(20);
        executor.remove(r);
    }

    private static void testAllowCoreThreadTimeOut() {
        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(5);
        executor.setKeepAliveTime(10, TimeUnit.SECONDS);
        executor.allowCoreThreadTimeOut(true);
        IntStream.range(0, 5).boxed().forEach(i -> {
            executor.execute(() -> {
                sleepSeconds(3);
            });
        });

    }

    private static void test() {
        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(5);
        System.out.println(executor.getActiveCount());

        executor.execute(() -> {
            sleepSeconds(10);
        });

        sleepMilliSeconds(20);

        System.out.println(executor.getActiveCount());

    }

    private static void testThreadPoolAdvice() {
        ThreadPoolExecutor executor = new MyThreadPoolExecutor(
                1,
                2,
                30,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(1),
                r -> {
                    Thread thread = new Thread(r);
                    return thread;
                },
                new ThreadPoolExecutor.AbortPolicy());
        executor.execute(new MyRunnable(1) {
            @Override
            public void run() {
//                System.out.println("==================");
                System.out.println(1/0);
            }
        });
    }

    private abstract static class MyRunnable implements Runnable {

        private final int no;

        public MyRunnable(int no) {
            this.no = no;
        }

        protected int getData() {
            return this.no;
        }
    }

    private static class MyThreadPoolExecutor extends ThreadPoolExecutor {
        public MyThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, ThreadFactory threadFactory, RejectedExecutionHandler handler) {
            super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory, handler);
        }

        @Override
        protected void beforeExecute(Thread t, Runnable r) {
            System.out.println("init the " + ((MyRunnable) r).getData());
        }

        @Override
        protected void afterExecute(Runnable r, Throwable t) {
            if (null == t) {
                System.out.println("successfully " + ((MyRunnable) r).getData());
            } else {
                t.printStackTrace();
            }
        }
    }

    private static void sleepSeconds(long seconds) {
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void sleepMilliSeconds(long milliSeconds) {
        try {
            TimeUnit.MILLISECONDS.sleep(milliSeconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
