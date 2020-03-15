package com.majia.concurrentpackage.concurrenttool.phaser;

import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;

public class PhaserExample8 {

    public static void main(String[] args) throws InterruptedException {
        Phaser phaser = new Phaser(3);

        Thread thread = new Thread(phaser::arriveAndAwaitAdvance);
        thread.start();

        TimeUnit.SECONDS.sleep(3);
        System.out.println(phaser.isTerminated());

        phaser.forceTermination();
        System.out.println(phaser.isTerminated());
    }
}
