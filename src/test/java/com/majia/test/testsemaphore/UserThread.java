package com.majia.test.testsemaphore;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class UserThread extends Thread {

    private final BoundedResources resources;

    private final static Random random = new Random(System.currentTimeMillis());


    public UserThread(BoundedResources resources) {
        this.resources = resources;
    }

    @Override
    public void run() {
        try {
            while (true){
                resources.use();
                TimeUnit.MILLISECONDS.sleep(random.nextInt(3000));
            }
        }catch (InterruptedException e){
            e.printStackTrace();
        }

    }
}
