package com.majia.basethread.produceandconsumer;

import java.util.stream.Stream;

public class ProduceConsumerVersion3 {
    private int i = 0;
    private final Object LOCK = new Object();
    private volatile boolean isProduced = false;

    private void produce(){
        synchronized (LOCK){
            while (isProduced){
                try {
                    LOCK.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            i++;
            System.out.println("P-> " + i);
            LOCK.notifyAll();
            isProduced = true;

        }
    }

    private void consume(){
        synchronized (LOCK){
            while (!isProduced){
                try {
                    LOCK.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            System.out.println("C-> " + i);
            LOCK.notifyAll();
            isProduced = false;

        }
    }

    public static void main(String[] args) {
        ProduceConsumerVersion3 pc = new ProduceConsumerVersion3();
        Stream.of("P1","P2","P3","P4").forEach(p->{
            new Thread(p){
                @Override
                public void run() {
                    while (true){
                        pc.produce();
                    }
                }
            }.start();
        });

        Stream.of("C1","C2","C3","C4").forEach(c->{
            new Thread(c){
                @Override
                public void run() {
                    while (true){
                        pc.consume();
                    }
                }
            }.start();
        });

    }
}
