package com.majia.designpattern.twophasetermination;

import java.util.Random;

public class CounterIncrement extends Thread {

    private volatile boolean terminated = false;

    private int counter = 0;

    private static final Random radom = new Random(System.currentTimeMillis());

    @Override
    public void run() {
        try {
            while (!terminated) {
                System.out.println(Thread.currentThread().getName() + " " + counter++);
                Thread.sleep(radom.nextInt(1000));
            }
        } catch (InterruptedException e) {
//            e.printStackTrace();
        } finally {
            this.clean();
        }
    }

    private void clean() {
        System.out.println("do some clean work second phase,current counter " + counter);
    }

    public void close() {
        this.terminated = true;
        this.interrupt();
    }
}
