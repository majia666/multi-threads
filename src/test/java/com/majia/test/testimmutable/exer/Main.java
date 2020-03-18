package com.majia.test.testimmutable.exer;

import java.sql.SQLOutput;

public class Main {
    private final static long CALL_COUNT = 1000000000L;

    public static void main(String[] args) {

        trail("NotSynch",CALL_COUNT,new NotSynch());
        trail("Synch",CALL_COUNT,new Synch());
    }

    private static void trail(String msg, long count, Object object) {
        System.out.println(msg + " : BEGIN");
        long start_time = System.currentTimeMillis();

        for (int i = 0; i < count; i++) {
            object.toString();
        }
        System.out.println(msg + " : END");
        System.out.println("Elapsed time = " + (System.currentTimeMillis() - start_time) + "msec.");

    }
}
