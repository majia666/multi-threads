package com.majia.firstpart.chapter03;

import java.util.concurrent.TimeUnit;

public class ThreadIsInterrupted {
    public static void main(String[] args) throws InterruptedException {
       /* Thread thread = new Thread() {
            @Override
            public void run() {
                while (true){
                    //do nothing,just empty loop
                }
            }
        };
        thread.start();
        TimeUnit.MILLISECONDS.sleep(2);
        System.out.printf("Thread is interrupted ? %s\n",thread.isInterrupted());
        thread.interrupt();
        System.out.printf("Thread is interrupted ? %s\n",thread.isInterrupted());*/

        Thread thread = new Thread() {
            @Override
            public void run() {
                try {
                    TimeUnit.MINUTES.sleep(1);
                } catch (InterruptedException e) {
                    // ignore the exception
                    // here the interrupt flag will be clear.
                    System.out.printf("I am be interrupted ? %s\n",isInterrupted());
                }
            }
        };
        thread.setDaemon(true);
        thread.start();
        TimeUnit.MILLISECONDS.sleep(2);
        System.out.printf("Thread is interrupted ? %s\n",thread.isInterrupted());
        thread.interrupt();
        TimeUnit.MILLISECONDS.sleep(2);
        System.out.printf("Thread is interrupted ? %s\n",thread.isInterrupted());

    }
}
