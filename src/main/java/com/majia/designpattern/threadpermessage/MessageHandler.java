package com.majia.designpattern.threadpermessage;

import java.util.Random;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MessageHandler {

    private static final Random random = new Random(System.currentTimeMillis());

    private static final Executor executor = Executors.newFixedThreadPool(5);

    public void request(Message message) {

        executor.execute(() -> {
            String value = message.getValue();
            try {
                Thread.sleep(random.nextInt(1000));
                System.out.println("The message will handler by " + Thread.currentThread().getName() + " " + value);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

    }

    public void shutdown(){
        ((ExecutorService)executor).shutdown();
    }
}
