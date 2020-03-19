package com.majia.designpattern.produceandconsumer.makereater;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class MakerThread extends Thread {

    private final Table table;

    private static int no = 0; // 蛋糕流水号

    private final static Random random = new Random(System.currentTimeMillis());


    public MakerThread(String name, Table table) {
        super("Maker-" + name);
        this.table = table;
    }

    @Override
    public void run() {
        try {
            while (true) {
                TimeUnit.MILLISECONDS.sleep(random.nextInt(1000));
                String cake = "Cake No." + nextNo();
                table.put(cake);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static synchronized  int nextNo() {
        return no++;
    }
}
