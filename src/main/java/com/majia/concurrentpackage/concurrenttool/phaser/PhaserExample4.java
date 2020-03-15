package com.majia.concurrentpackage.concurrenttool.phaser;

import java.util.Random;
import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;

public class PhaserExample4 {

    private static final Random random = new Random(System.currentTimeMillis());

    public static void main(String[] args) {

        Phaser phaser = new Phaser(1);

     /*   phaser.arriveAndAwaitAdvance();
        System.out.println(phaser.getPhase());

        phaser.arriveAndAwaitAdvance();
        System.out.println(phaser.getPhase());

        phaser.arriveAndAwaitAdvance();
        System.out.println(phaser.getPhase());*/

        System.out.println(phaser.getRegisteredParties());
        phaser.register();
        System.out.println(phaser.getRegisteredParties());
        phaser.register();
        System.out.println(phaser.getRegisteredParties());


    }



}
