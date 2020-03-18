package com.majia.test.testsemaphore;

import org.junit.Test;

import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

public class BoundedResources {

    private final Semaphore semaphore;

    private final int permits;

    private final static Random random = new Random(System.currentTimeMillis());

    public BoundedResources(int permits) {
        this.semaphore = new Semaphore(permits);
        this.permits = permits;
    }
    public void use(){
        try {
            semaphore.acquire();
            doUse();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            semaphore.release();
        }
    }

    private void doUse() throws InterruptedException {

        System.out.println(Thread.currentThread().getName() + ": " + " BEGIN: used= " + (permits-semaphore.availablePermits()));
        TimeUnit.MILLISECONDS.sleep(random.nextInt(5000));
        System.out.println(Thread.currentThread().getName() + ": " + " END: used= " + (permits-semaphore.availablePermits()));

    }

    public static void main(String[] args) {
        BoundedResources resources = new BoundedResources(3);

        IntStream.range(0,10).forEach(i->{
            // System.out.println(i);
            new UserThread(resources).start();
        });
    }
}
