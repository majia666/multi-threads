package com.majia.firstpart.chapter01;

import java.util.concurrent.TimeUnit;

public class TryConcurrency {
    /**
     * 实现听音乐和看新闻两个任务同时执行
     * @param args
     */
    public static void main(String[] args) {
        /**
         * 1.串行
         */
        //browseNews();
        //enjoyMusic();
        /**
         * 2.并行
         */
        // 通过匿名内部类的方式创建线程，并且重写其中的run方法
//        new Thread(){             // ①
//            @Override
//            public void run() {
//                enjoyMusic();
//            }
//        }.start();
//        browseNews();            // ②
        // java8 lambda 表达式改造
//        new Thread(TryConcurrency::enjoyMusic).start();
//        browseNews();
        Thread thread = new Thread() {
            @Override
            public void run() {
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        thread.start();// 启动线程
        thread.start();// 再次启动
    }

    /**
     * Browse the latest news.
     */
    private static void browseNews(){
        for (; ; ) {
            System.out.println("Uh-huh,the goods news.");
            sleep(1);
        }
    }

    /**
     * Listening and enjoy music.
     */
    private static void enjoyMusic(){
        for (; ;) {
            System.out.println("Uh-huh,the nice music.");
            sleep(1);
        }
    }

    /**
     * Simulate the wait and ignore exception
     * @param seconds
     */
    private static void sleep(int seconds){
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
