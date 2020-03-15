package com.majia.basethread.createthread;


import org.apache.log4j.Logger;

public class CreateNewThread {
    private static  Logger logger = Logger.getLogger(CreateNewThread.class.getClass());
    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                logger.info("thread name is : "  + Thread.currentThread().getName());
            }
        }).start();
    }
}
