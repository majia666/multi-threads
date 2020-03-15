package com.majia.basethread.threadgroup;

import java.util.Arrays;

public class ThreadGroupCreate {
    public static void main(String[] args) {
        ThreadGroup tg1 = new ThreadGroup("TG1");
        Thread t1 = new Thread(tg1, "t1") {
            @Override
            public void run() {
                while (true){
                    try {
//                        System.out.println(getThreadGroup().getName());
//                        System.out.println(getThreadGroup().getParent());
                        Thread.sleep(10_000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }
        };
        t1.start();
        ThreadGroup tg2 = new ThreadGroup("TG2");
        Thread t2 = new Thread(tg2,"t2"){
            @Override
            public void run() {
                System.out.println(">>>" + tg1.getName());
                Thread[] threads = new Thread[tg1.activeCount()];
                int enumerate = tg1.enumerate(threads);
                Arrays.asList(threads).forEach(System.out::println);
            }
        };
        t2.start();

//        ThreadGroup tg2 = new ThreadGroup(tg1,"TG2");
//        System.out.println("tg2 naeme: " + tg2.getName());
//        System.out.println("tg2 parent: " + tg2.getParent());
    }
}
