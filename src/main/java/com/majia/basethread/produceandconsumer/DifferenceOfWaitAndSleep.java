package com.majia.basethread.produceandconsumer;

public class DifferenceOfWaitAndSleep {

    private static final Object LOCK = new Object();
    public static void main(String[] args) {
        m1();
    }

    public static void m1(){
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void m2(){
        synchronized (LOCK){
            try {
                LOCK.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
