package com.majia.concurrentpackage.concurrenttool.phaser;

import java.util.Random;
import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;

public class PhaserExample5 {

    private static final Random random = new Random(System.currentTimeMillis());

    /**
     * arrive
     * @param args
     */
    public static void main(String[] args) {
        Phaser phaser = new Phaser(5);
        for (int i = 0; i <4 ; i++) {
            new ArriveTask(phaser,i).start();
        }

        phaser.arriveAndAwaitAdvance();

        System.out.println("The phase 1 work finished done.");

    }

    private static class ArriveTask extends Thread{
        private final Phaser phaser;


        private ArriveTask(Phaser phaser,int no) {
            super(String.valueOf(no));
            this.phaser = phaser;
        }

        @Override
        public void run() {

            System.out.println(getName() + " start working." );

            sleepSeconds();
            System.out.println(getName() + " the phase one is running.");
            phaser.arrive();

            sleepSeconds();
            System.out.println(getName()+" keep to do other thing.");
        }


    }

    private static void sleepSeconds(){
        try {
            TimeUnit.SECONDS.sleep(random.nextInt(5));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
