package com.majia.concurrentpackage.concurrenttool.forkjoin;

import java.sql.Time;
import java.util.Optional;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

public class ForkJoinRecursiveAction {

    private static final int MAX_THRESHOLD = 3;

    private static final AtomicInteger SUM = new AtomicInteger(0);

    public static void main(String[] args) throws InterruptedException {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        forkJoinPool.submit(new CalculateRecursiveAction(0, 10));
        forkJoinPool.awaitTermination(10, TimeUnit.SECONDS);

        Optional.of(SUM).ifPresent(System.out::println);

    }

    private static class CalculateRecursiveAction extends RecursiveAction {

        private final int start;
        private final int end;

        private CalculateRecursiveAction(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        protected void compute() {
            if (end - start <= MAX_THRESHOLD) {
                SUM.addAndGet(IntStream.rangeClosed(start, end).sum());
            } else {
                int middle = (start + end) / 2;
                CalculateRecursiveAction leftAction = new CalculateRecursiveAction(start, middle);
                CalculateRecursiveAction rightAction = new CalculateRecursiveAction(middle + 1, end);
                leftAction.fork();
                rightAction.fork();

            }
        }
    }
}
