package com.majia.designpattern.balking;

import java.io.IOException;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class ChangerThread extends Thread {

    private final BalkingData data;

    private final static Random random = new Random(System.currentTimeMillis());

    public ChangerThread(String name,BalkingData data) {
        super(name);
        this.data = data;
    }

    @Override
    public void run() {
        try{
            for (int i = 0; true ; i++) {
                data.change("No." + i);
                TimeUnit.MILLISECONDS.sleep(random.nextInt(1000));
                data.save();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
