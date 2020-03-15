package com.majia.basethread.threadexception;

public class ThreadException {
    private static int A = 10;
    private static int B = 0;
    public static void main(String[] args) {
        Thread thread = new Thread(()->{
            try {
                Thread.sleep(10_000);
                int result = A / B;
                System.out.println(result);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        thread.setUncaughtExceptionHandler((t,e)->{
            System.out.println(e);
            System.out.println(t);
        });
        thread.start();
    }
}
