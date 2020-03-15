package com.majia.basethread.daemonthread;

import org.apache.log4j.Logger;

public class DaemonThread {
    private static Logger logger = Logger.getLogger(DaemonThread.class.getClass());
    public static void main(String[] args){
        Thread t = new Thread(()->{
            Thread innerThread = new Thread(()->{

                try {
                    while (true){
                        logger.debug("daemon thread health");
                        Thread.sleep(1000);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
            innerThread.start();
            innerThread.setDaemon(true);

        });
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t.setDaemon(true);
        t.start();
    }
}
