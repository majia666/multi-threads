package com.majia.designpattern.balking;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class ServerThread extends Thread {

    private final BalkingData data;

    public ServerThread(String name,BalkingData data) {
        super(name);
        this.data = data;
    }

    @Override
    public void run() {
        try{
            while (true){
                data.save();
                TimeUnit.SECONDS.sleep(1);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
