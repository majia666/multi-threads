package com.majia.concurrentpackage.concurrenttool.locks;

import javax.swing.text.html.Option;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class ReentrantLockExample {

    private static final Lock lock = new ReentrantLock();

    public static void main(String[] args) {
        IntStream.range(0,2).forEach(i->{
            new Thread(()->{
                needLock();
            }).start();
        });
    }


    public static void testUnInterruptibly() {
        try{

            lock.lockInterruptibly();
            Optional.of(Thread.currentThread().getName() + " get lock and will do working.").ifPresent(System.out::println);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
    public static void needLock() {
        try{
            lock.lock();
            Optional.of(Thread.currentThread().getName() + " get lock and will do working.").ifPresent(System.out::println);
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public static void needLockBySync(){
        synchronized (ReentrantLockExample.class){
            try {
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
