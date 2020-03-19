package com.majia.designpattern.produceandconsumer.makereater;

import org.omg.CORBA.TIMEOUT;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class EaterThread extends Thread {

    private final Table table;

    private final static Random random = new Random(System.currentTimeMillis());


    public EaterThread(String name,Table table) {
        super("Eater-" + name);
        this.table = table;
    }

    @Override
    public void run() {
        try {
            while (true){
                String take = table.take();
                TimeUnit.MILLISECONDS.sleep(random.nextInt(1000));
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
