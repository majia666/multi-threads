package com.majia.designpattern.produceandconsumer;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class ProducerThread extends Thread {

    private final MessageQueue queue;

    private static final Random radom = new Random(System.currentTimeMillis());

    private static final AtomicInteger counter = new AtomicInteger(0);

    public ProducerThread(MessageQueue queue, int seq) {
        super("PRODUCE-" + seq);
        this.queue = queue;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Message message = new Message("Message-" + counter.getAndIncrement());
                queue.put(message);
                System.out.println(Thread.currentThread().getName()+ " put message " + message.getData());
                Thread.sleep(radom.nextInt(1000));
            } catch (InterruptedException e) {
                break;
            }
        }
    }
}
