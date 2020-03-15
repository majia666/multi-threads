package com.majia.designpattern.threadlocal;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * 始终以当前线程作为key值
 * @param <T>
 */
public class ThreadLocalSimulatorTest<T> {

    private static final ThreadLocalSimulator<String> local = new ThreadLocalSimulator<String>(){
        @Override
        public String initialValue() {
            return "No value";
        }
    };

    private static final Random radom = new Random(System.currentTimeMillis());

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            local.set("Thread-1");
            try {
                Thread.sleep(radom.nextInt(1000));
                System.out.println(Thread.currentThread().getName() + " " + local.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread t2 = new Thread(() -> {
            local.set("Thread-2");
            try {
                Thread.sleep(radom.nextInt(1000));
                System.out.println(Thread.currentThread().getName() + " " + local.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println("==========================================");
        System.out.println(Thread.currentThread().getName() + " " + local.get());
    }
}
