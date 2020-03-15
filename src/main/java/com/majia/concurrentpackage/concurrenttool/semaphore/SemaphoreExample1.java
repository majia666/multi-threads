package com.majia.concurrentpackage.concurrenttool.semaphore;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class SemaphoreExample1 {

    public static void main(String[] args) {
//        Semaphore semaphore = new Semaphore(1);
        SemaphoreLock lock = new SemaphoreLock();

        new Thread(()->{
            System.out.println(Thread.currentThread().getName() + " is Running.");
            try {
                lock.lock();
                System.out.println(Thread.currentThread().getName() + " get the #SemaphoreLock.");

                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                lock.unlock();
            }
            System.out.println(Thread.currentThread().getName() + " released the #SemaphoreLock.");
        }).start();
    }

    static class SemaphoreLock{

        private final Semaphore semaphore = new Semaphore(1);

        public void lock() throws InterruptedException {
            semaphore.acquire();
        }

        public void unlock(){
            semaphore.release();
        }
    }
}
