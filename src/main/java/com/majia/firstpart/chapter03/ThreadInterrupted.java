package com.majia.firstpart.chapter03;

import java.util.concurrent.TimeUnit;

public class ThreadInterrupted {
    public static void main(String[] args) throws InterruptedException {
        /*Thread thread = new Thread() {
            @Override
            public void run() {
                while (true){
                    System.out.println(Thread.interrupted());
                }
            }

        };
        thread.setDaemon(true);
        thread.start();
        //shortly block make sure the thread is started.
        TimeUnit.MILLISECONDS.sleep(2);
        thread.interrupt();*/

        // ① 判断当前线程是否被中断
        System.out.println("Main thread is interrupted ? " + Thread.interrupted());
        // ② 中断当前线程
        Thread.currentThread().interrupt();
        // ③ 判断当前线程是否已经被中断
        System.out.println("Main thread is interrupted ? " + Thread.currentThread().isInterrupted());
        try {
            // ④ 当前线程执行可中断方法
            TimeUnit.MINUTES.sleep(1);
        }catch (InterruptedException e){
            // ⑤ 捕获中断信号
            System.out.println("I will be interrupted still.");
        }


    }
}
