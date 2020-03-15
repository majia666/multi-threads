package com.majia.concurrentpackage.concurrenttool.phaser;

import java.util.Random;
import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

public class PhaserExample6 {

    private static final Random random = new Random(System.currentTimeMillis());

    /**
     * AwaitAdvance
     *
     * @param args
     */
    public static void main(String[] args) {

        Phaser phaser = new Phaser(7);

        IntStream.rangeClosed(0,5).boxed().map(i->phaser).forEach(AwaitAdvanceTask::new);

        phaser.awaitAdvance(phaser.getPhase());

        System.out.println("=================");
    }

    private static class AwaitAdvanceTask extends Thread {
        private final Phaser phaser;


        private AwaitAdvanceTask(Phaser phaser) {
            this.phaser = phaser;
            start();
        }

        @Override
        public void run() {

            sleepSeconds();
            phaser.arriveAndAwaitAdvance();
            System.out.println(getName() + " finished work.");
        }


    }

    private static void sleepSeconds() {
        try {
            TimeUnit.SECONDS.sleep(random.nextInt(5));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
