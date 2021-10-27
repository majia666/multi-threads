package com.majia.firstpart.chapter04;

public class TicketWindowRunnable implements Runnable {

    private int index = 1;
    private final static int MAX = 500;
    private final static Object MUTEX =  new Object();
    @Override
    public void run() {
        synchronized (MUTEX){
            while (index <= MAX){
                System.out.println(Thread.currentThread() + " 的号码是：" + (index++));
            }
        }

    }

    public static void main(String[] args) {
        final TicketWindowRunnable task = new TicketWindowRunnable();
        Thread windownThread1 = new Thread(task, "一号窗口");
        Thread windownThread2 = new Thread(task, "二号窗口");
        Thread windownThread3 = new Thread(task, "三号窗口");
        Thread windownThread4 = new Thread(task, "四号窗口");
        windownThread1.start();
        windownThread2.start();
        windownThread3.start();
        windownThread4.start();
    }
}
