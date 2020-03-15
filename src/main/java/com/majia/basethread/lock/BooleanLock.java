package com.majia.basethread.lock;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

public class BooleanLock implements Lock {

    // 初始值为true，lock 已经被取走，为false时 可以被其他线程取lock
    private boolean initValue;

    private Collection<Thread> blockedThreadCollection =  new ArrayList<Thread>();

    private Thread currentThread;

    public BooleanLock() {
        this.initValue = false;
    }

    @Override
    public synchronized void lock() throws InterruptedException {
        while (initValue){
            this.wait();
            blockedThreadCollection.add(Thread.currentThread());
        }
        blockedThreadCollection.remove(Thread.currentThread());
        initValue = true;
        this.currentThread = Thread.currentThread();
    }

    @Override
    public synchronized void lock(Long mills) throws InterruptedException ,TimeOutException{
        if (mills <= 0){
            lock();
        }else{
            Long hasReamingTime = mills;
            Long endTime = System.currentTimeMillis() + mills;
            while (initValue){
                if(hasReamingTime <= 0){
                    throw  new TimeOutException("Time Out");
                }
                this.wait(mills);
                blockedThreadCollection.add(Thread.currentThread());
                hasReamingTime = endTime - System.currentTimeMillis();
            }
            blockedThreadCollection.remove(Thread.currentThread());
            initValue = true;
            this.currentThread = Thread.currentThread();
        }
    }

    @Override
    public synchronized void unlock() {
        if(currentThread == Thread.currentThread()){
            initValue = false;
            Optional.of(Thread.currentThread().getName() + " release the lock monitor.").ifPresent(System.out::println);
            this.notifyAll();
        }

    }

    @Override
    public Collection<Thread> getBlockedThread() {
        return Collections.unmodifiableCollection(blockedThreadCollection);
    }

    @Override
    public int getBlockedSize() {
        return blockedThreadCollection.size();
    }
}
