package com.majia.basethread.threadgroup;

public class ThreadGroupApi {
    public static void main(String[] args) {
        ThreadGroup tg1 = new ThreadGroup("TG1");
        new Thread(tg1,"T1"){
            @Override
            public void run() {
                while (true){
                    try {
                        Thread.sleep(10_1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();

        ThreadGroup tg2 = new ThreadGroup(tg1,"TG2");
        new Thread(tg2,"T2"){
            @Override
            public void run() {
                while (true){
                    try {
                        Thread.sleep(10_1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();

        System.out.println(tg1.activeCount());
    }
}
