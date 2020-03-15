package com.majia.concurrentpackage.atomic.integer;

import java.util.concurrent.atomic.AtomicInteger;

public class CompareAndSetLock {

    private final AtomicInteger value = new AtomicInteger();

    private Thread lockedThread;

    public void tryLock() throws GetLockException {
        boolean result = value.compareAndSet(0, 1);
        if (!result){
            throw new GetLockException("Get the lock failed.");
        }else{
            lockedThread = Thread.currentThread();
        }
    }

    public void unlock(){

        if (0 == value.get()){
            return;
        }
        if (lockedThread == Thread.currentThread()){
            value.compareAndSet(1, 0);
        }

    }
}
