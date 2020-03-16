package com.majia.designpattern.countdown;

import java.util.Random;
import java.util.stream.IntStream;

public class CustomCountDownClient {
    private static final Random random = new Random(System.currentTimeMillis());

    public static void main(String[] args) throws InterruptedException {

        final CountDown latch = new CountDown(5);
        System.out.println("准备多线程处理任务");
        // 第一阶段
        IntStream.rangeClosed(1,5).forEach(i->{
            new Thread(()->{
                System.out.println(Thread.currentThread().getName() + " is working.");
                try {
                    Thread.sleep(random.nextInt(1000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                latch.down();
            },String.valueOf(i)).start();
        });
        latch.await();
        // 第二阶段
        System.out.println("多线程全部准备结束，准备第二阶段任务");
        System.out.println("........................................");
        System.out.println("FINISH");
    }
}