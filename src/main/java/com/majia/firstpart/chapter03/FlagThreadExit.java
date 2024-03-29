package com.majia.firstpart.chapter03;

import java.util.concurrent.TimeUnit;

public class FlagThreadExit {
    static class MyTask extends Thread{
        private volatile boolean closed = false;

        @Override
        public void run() {
            System.out.println("I will start work.");
            while (!closed && !isInterrupted()){
                //正在运行
            }
            System.out.println("I will be exiting.");
        }
        public void close(){
            this.closed = true;
            this.interrupt();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        MyTask myTask = new MyTask();
        myTask.start();
        TimeUnit.MINUTES.sleep(1);
        System.out.println("System will be shutdown.");
        myTask.close();
    }
}
