package com.majia.basethread.lock;

import java.util.Optional;
import java.util.stream.Stream;

public class LockTest {
    public static void main(String[] args) {
       final BooleanLock booleanLock = new BooleanLock();
        Stream.of("T1","T2","T3","T4","T5").forEach(name->{
            new Thread(()->{
                try {
                    booleanLock.lock(10L);
                    Optional.of(Thread.currentThread().getName() + " have the lock Monitor.").ifPresent(System.out::println);
                    work();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }catch (Lock.TimeOutException e){
                    Optional.of(Thread.currentThread().getName() +" time out").ifPresent(System.out::println);
                }finally {
                    booleanLock.unlock();
                }
            },name).start();
            try {
                Thread.sleep(100);
                booleanLock.unlock();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }

    private static void work(){
        Optional.of(Thread.currentThread().getName() + " is working...").ifPresent(System.out::println);
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
