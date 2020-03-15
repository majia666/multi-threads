package com.majia.concurrentpackage.concurrenttool.forkjoin;

import com.majia.designpattern.threadpermessage.Message;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;
import java.util.stream.IntStream;

public class ForkJonRecursiveTask {

    private static final int MAX_THRESHOLD = 3;

    public static void main(String[] args) {
        ForkJoinPool forkJoinPool = new ForkJoinPool();

        ForkJoinTask<Integer> future = forkJoinPool.submit(new CalculatedRecursiveTask(0, 10000));

        try {
            Integer result = future.get();
            System.out.println(result);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    private static class CalculatedRecursiveTask extends RecursiveTask<Integer> {

        private final int start;
        private final int end;

        public CalculatedRecursiveTask(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        protected Integer compute() {
            if (end - start <= MAX_THRESHOLD) {
                return IntStream.rangeClosed(start, end).sum();
            } else {
                int middle = (start + end) / 2;

                CalculatedRecursiveTask leftTask = new CalculatedRecursiveTask(start, middle);
                CalculatedRecursiveTask rightTask = new CalculatedRecursiveTask(middle + 1, end);
                leftTask.fork();
                rightTask.fork();
                return leftTask.join() + rightTask.join();
            }
        }
    }
}
