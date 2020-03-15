package com.majia.basethread.produceandconsumer;

import java.util.stream.Stream;

public class ProduceConsumerVersion2 {
    private int i = 0;
    private final Object LOCK = new Object();
    private volatile boolean isProduced = false;

    private void produce(){
        synchronized (LOCK){
            if(isProduced){
                try {
                    LOCK.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }else{
                i++;
                System.out.println("P-> " + i);
                LOCK.notify();
                isProduced = true;
            }
        }
    }

    private void consume(){
        synchronized (LOCK){
            if(isProduced){
                System.out.println("C-> " + i);
                LOCK.notify();
                isProduced = false;
            }else {
                try {
                    LOCK.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        ProduceConsumerVersion2 pc = new ProduceConsumerVersion2();
        Stream.of("P1","P2").forEach(p->{
            new Thread(p){
                @Override
                public void run() {
                    while (true){
                        pc.produce();
                    }
                }
            }.start();
        });

        Stream.of("C1","C2").forEach(c->{
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
