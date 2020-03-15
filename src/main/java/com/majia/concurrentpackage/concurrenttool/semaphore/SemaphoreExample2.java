package com.majia.concurrentpackage.concurrenttool.semaphore;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class SemaphoreExample2 {

    public static void main(String[] args) throws InterruptedException {
        Semaphore semaphore = new Semaphore(2);

        for (int i = 0; i < 3; i++) {
            new Thread(()->{
                System.out.println(Thread.currentThread().getName() +" in.");
                try {
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName() +" Get the semaphore.");
                    TimeUnit.SECONDS.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    semaphore.release();
                }
                System.out.println(Thread.currentThread().getName() +" out.");
            }).start();
        }
        while (true){
            System.out.println("AP-> " + semaphore.availablePermits());
            System.out.println("QL-> " + semaphore.getQueueLength());
            TimeUnit.SECONDS.sleep(1);
        }
    }
}
