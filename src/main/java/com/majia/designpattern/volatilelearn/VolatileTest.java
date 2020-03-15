package com.majia.designpattern.volatilelearn;

public class VolatileTest {

    private static volatile int INIT_VALUE =0;

    private static final int MAX_LIMIT = 50;

    public static void main(String[] args) {
        new Thread(()->{
            int localValue = INIT_VALUE;
            while (INIT_VALUE < MAX_LIMIT){
                if (localValue != INIT_VALUE ){
                    System.out.printf("The value update to [%d]\n",INIT_VALUE);
                    localValue = INIT_VALUE;
                }
            }
        },"READER").start();



        new Thread(()->{
            int localValue = INIT_VALUE;
            while (INIT_VALUE < MAX_LIMIT){
                System.out.printf("Update value update to [%d]\n",++localValue);
                INIT_VALUE  = localValue;
                try {
                    Thread.sleep(5_00);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"UPDATER").start();
    }
}
