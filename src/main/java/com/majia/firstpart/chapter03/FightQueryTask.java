package com.majia.firstpart.chapter03;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class FightQueryTask extends Thread implements FightQuery {

    private final String origion;

    private final String destination;

    private final List<String> fightList = new ArrayList<String>();

    public FightQueryTask(String airLine,String origion, String destination) {
        super("[" + airLine +"]");
        this.origion = origion;
        this.destination = destination;
    }

    @Override
    public void run() {
        System.out.printf("%s-query from %s to %s \n",getName(),origion,destination);
        int randomVal = ThreadLocalRandom.current().nextInt(10);

        try {
            TimeUnit.SECONDS.sleep(randomVal);
            this.fightList.add(getName() + "-" + randomVal);
            System.out.printf("The Fight : %s list query successful\n",getName());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    @Override
    public List<String> get() {
        return this.fightList;
    }

}
