package com.majia.concurrentpackage.concurrenttool.exchanger;

import java.util.concurrent.Exchanger;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

public class ExchangerExample3 {

    public static void main(String[] args) {
        Exchanger<Integer> exchanger = new Exchanger<Integer>();

        new Thread(() -> {
            AtomicReference<Integer> value = new AtomicReference<Integer>(1);
            try {
                while (true) {
                    value.set(exchanger.exchange(value.get()));
                    System.out.println(Thread.currentThread().getName() + " value: " + value.get());
                    TimeUnit.SECONDS.sleep(2);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "==A==").start();

        new Thread(() -> {
            AtomicReference<Integer> value = new AtomicReference<Integer>(2);
            try {
                while (true) {
                    value.set(exchanger.exchange(value.get()));
                    System.out.println(Thread.currentThread().getName() + " value: " + value.get());
                    TimeUnit.SECONDS.sleep(3);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "==B==").start();
    }
}
