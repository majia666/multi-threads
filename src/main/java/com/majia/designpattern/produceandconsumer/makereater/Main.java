package com.majia.designpattern.produceandconsumer.makereater;

import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] args) {
        Table table = new Table(3);

        new MakerThread(Thread.currentThread().getName(),table).start();
        new MakerThread(Thread.currentThread().getName(),table).start();
        new MakerThread(Thread.currentThread().getName(),table).start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new EaterThread(Thread.currentThread().getName(),table).start();
        new EaterThread(Thread.currentThread().getName(),table).start();
        new EaterThread(Thread.currentThread().getName(),table).start();
    }
}
