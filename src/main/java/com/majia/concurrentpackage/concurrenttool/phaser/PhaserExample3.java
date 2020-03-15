package com.majia.concurrentpackage.concurrenttool.phaser;

import java.util.Random;
import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;

public class PhaserExample3 {

    private static final Random random = new Random(System.currentTimeMillis());

    public static void main(String[] args) {

        Phaser phaser = new Phaser(5);
        for (int i = 1; i < 6; i++) {
            new Athletes(i,phaser).start();
        }

        new InjuredAthletes(6,phaser).start();
    }


    static class InjuredAthletes extends Thread{
        private final int no;
        private final Phaser phaser;

        InjuredAthletes(int no, Phaser phaser) {
            this.no = no;
            this.phaser = phaser;
        }

        @Override
        public void run() {
            try {
                sport(no, phaser, ": start running....", ": end running....");

                sport(no, phaser, ": start bicycle....", ": end bicycle....");

                System.out.println(no+ " Oh shit, i am injured,i will be exited");

                phaser.arriveAndDeregister();


            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    static class Athletes extends Thread{

        private final int no;
        private final Phaser phaser;

        Athletes(int no, Phaser phaser) {
            this.no = no;
            this.phaser = phaser;
        }

        @Override
        public void run() {
            try {
                sport(no, phaser, ": start running....", ": end running....");

                sport(no, phaser, ": start bicycle....", ": end bicycle....");

                sport(no, phaser, ": start long jump....", ": end long jump....");


            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
    private static void sport(int no, Phaser phaser, String s, String s2) throws InterruptedException {
        System.out.println(no + s);
        TimeUnit.SECONDS.sleep(random.nextInt(5));
        System.out.println(no + s2);
        phaser.arriveAndAwaitAdvance();
    }
}
