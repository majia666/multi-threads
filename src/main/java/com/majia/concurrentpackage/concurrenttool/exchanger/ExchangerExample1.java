package com.majia.concurrentpackage.concurrenttool.exchanger;

import com.majia.designpattern.worker.TransportThread;

import java.util.concurrent.Exchanger;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class ExchangerExample1 {

    public static void main(String[] args) {
        Exchanger<String> exchanger = new Exchanger<String>();

        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + " start.");
            try {
                String result = exchanger.exchange("I am come from T-A",10,TimeUnit.SECONDS);
                System.out.println(Thread.currentThread().getName() + " Get value [" + result + "]");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (TimeoutException e) {
                System.out.println("time out");
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+" end.");
        }, "==A==").start();

        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + " start.");
            try {
                TimeUnit.SECONDS.sleep(20);
                String result = exchanger.exchange("I am come from T-B");
                System.out.println(Thread.currentThread().getName() + " Get value [" + result + "]");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+" end.");
        }, "==B==").start();
    }
}
