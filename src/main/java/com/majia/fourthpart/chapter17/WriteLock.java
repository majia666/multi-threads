package com.majia.fourthpart.chapter17;

// WriteLock 被设计为包可见
class WriteLock implements Lock {
    private final ReadWriteLockImpl readWriteLock;

    WriteLock(ReadWriteLockImpl readWriteLock) {
        this.readWriteLock = readWriteLock;
    }

    @Override
    public void lock() throws InterruptedException {
        synchronized (readWriteLock.getMutex()){
           try{
               // 首先使等待获取写入锁数字加一
               readWriteLock.incrementWaitingWriters();
               // 如果此时有其他线程正在进行读操作，或者写操作，那么当前线程将被挂起
               while (readWriteLock.getReadingReaders()>0 || readWriteLock.getWritingWriters() > 0){
                   readWriteLock.getMutex().wait();
               }
           }finally {
               // 成功获取到了写入锁，使得等待获取写入锁的计数器减一
               this.readWriteLock.decrementWaitingWriters();
           }
           // 将正在写入的线程数量加一
            readWriteLock.incrementWritingWriters();

        }
    }

    @Override
    public void unlock() {
        synchronized (readWriteLock.getMutex()){
            // 减少正在写入锁的线程计数器
            readWriteLock.decrementWritingWriters();
            // 将偏好状态修改为false，可以使得读锁被最快速的获得
            readWriteLock.changePrefer(false);
            // 通知唤醒其他在Mutex monitor waitset 中的线程
            readWriteLock.getMutex().notifyAll();
        }
    }
}
