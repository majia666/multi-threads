package com.majia.designpattern.readerwriterlock;

/**
 * 读写锁设计模式 或者 读写设计模式
 */
public class ReadWriteLockClient {
    public static void main(String[] args) {
        SharedData data = new SharedData(10);
        new ReadWorker(data).start();
        new ReadWorker(data).start();
        new ReadWorker(data).start();
        new ReadWorker(data).start();
        new ReadWorker(data).start();
        new WriteWorker(data,"qwertyuiopasdfg").start();
        new WriteWorker(data,"QWERTYUIOPASDFG").start();
    }
}
