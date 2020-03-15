package com.majia.concurrentpackage.concurrenttool.phaser;

import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;

public class PhaserExample7 {

    public static void main(String[] args) throws InterruptedException {

//        Phaser phaser = new Phaser(3);
//        Thread thread = new Thread(phaser::arriveAndAwaitAdvance);
//        thread.start();
//        System.out.println("============");
//
//        TimeUnit.SECONDS.sleep(10);
//        thread.interrupt();
//        System.out.println("====thread interrupt====");
        Phaser phaser = new Phaser(3);
        Thread thread = new Thread(() -> {
            try {
                phaser.awaitAdvanceInterruptibly(phaser.getPhase());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        thread.start();
        System.out.println("============");
        TimeUnit.SECONDS.sleep(10);
        thread.interrupt();
        System.out.println("====thread interrupt====");
    }
}
