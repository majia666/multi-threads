package com.majia.designpattern.guardedsuspension;

import java.util.Random;

public class ServerThread extends Thread {

    private final RequestQueue queue;
    private final Random random;
    private volatile boolean flag = false;

    public ServerThread(RequestQueue queue) {
        this.queue = queue;
        random = new Random(System.currentTimeMillis());
    }


    @Override
    public void run() {
        while (!flag) {
            Request request = queue.getRequest();
            if (null == request){
                System.out.println("Received the empty request.");
                continue;
            }
            System.out.println("Server -> " + request.getValue());
            try {
                Thread.sleep(random.nextInt(1000));
            } catch (InterruptedException e) {
                //e.printStackTrace();
                break;
            }
        }
    }

    public void close() {
        this.flag = true;
        this.interrupt();
    }
}
