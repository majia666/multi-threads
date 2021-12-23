package com.majia.thirdpart.chapter13;

public class ThreadConsole extends Thread {
    // volatile 关键字保证可started线程的可见性
    private volatile boolean started = true;
    @Override
    public void run() {
        while (started){
            // do work
        }
    }

    public void shutdown(){
        this.started = false;
    }
}
