package com.majia.designpattern.produceandconsumer;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class ConsumerThread extends Thread {

    private final MessageQueue queue;

    private static final Random radom = new Random(System.currentTimeMillis());


    public ConsumerThread(MessageQueue queue, int seq) {
        super("CONSUMER-" + seq);
        this.queue = queue;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Message message = queue.take();
                System.out.println(Thread.currentThread().getName()+ " take message " + message.getData());
                Thread.sleep(radom.nextInt(1000));
            } catch (InterruptedException e) {
                break;
            }
        }
    }
}
