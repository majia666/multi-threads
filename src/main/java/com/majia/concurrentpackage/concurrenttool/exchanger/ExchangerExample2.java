package com.majia.concurrentpackage.concurrenttool.exchanger;

import java.util.concurrent.Exchanger;

public class ExchangerExample2 {

    public static void main(String[] args) {
        Exchanger<Object> exchanger = new Exchanger<>();

        new Thread(() -> {
            Object obj = new Object();
            System.out.println("A will send the object " + obj);
            try {
                Object result = exchanger.exchange(obj);
                System.out.println("A recived the object " + result);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "==A==").start();

        new Thread(() -> {
            Object obj = new Object();
            System.out.println("B will send the object " + obj);
            try {
                Object result = exchanger.exchange(obj);
                System.out.println("B recieved the object " + result);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "==B==").start();
    }
}
