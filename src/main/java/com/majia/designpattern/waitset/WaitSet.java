package com.majia.designpattern.waitset;

import java.util.Optional;
import java.util.stream.IntStream;

public class WaitSet {

    private static final Object LOCK = new Object();

    private static void work(){
        synchronized (LOCK){
            System.out.println("Begin.....");
            try {
                System.out.println("Thread will coming...");
                LOCK.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Thread will out...");
        }
    }
    public static void main(String[] args) {
        /**
         * 1.所有对象都会有一个wait set,用来存放调用了该对象wait方法之后进入的block状态的线程
         * 2.线程被notify 后，不一定立即得到执行
         * 3.线程从wait set 中被唤醒的顺序不一定是FIFO
         * 4.线程被唤醒后，必须重新获取锁
         */
        new Thread(){
            @Override
            public void run() {
                work();
            }
        }.start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        synchronized (LOCK){
            LOCK.notify();
        }

//        IntStream.rangeClosed(1,10).forEach(i->{
//            new Thread(String.valueOf(i)){
//                @Override
//                public void run() {
//                    synchronized (LOCK){
//                        try {
//                            Optional.of(Thread.currentThread().getName()+" will come to wait set.").ifPresent(System.out::println);
//                            LOCK.wait();
//                            Optional.of(Thread.currentThread().getName()+" will leave to wait set.").ifPresent(System.out::println);
//                        } catch (InterruptedException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                }
//            }.start();
//        });
//        try {
//            Thread.sleep(5_000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        IntStream.rangeClosed(1,10).forEach(i->{
//            new Thread(String.valueOf(i)){
//                @Override
//                public void run() {
//                    synchronized (LOCK){
//                        try {
//                           LOCK.notify();
//                           Thread.sleep(1000);
//                        } catch (InterruptedException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                }
//            }.start();
//        });
    }
}
