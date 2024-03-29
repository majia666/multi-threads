package com.majia.fourthpart.chapter17;

// 包可见，创建使用时ReadWriterLock 的 create 方法
class ReadWriteLockImpl implements ReadWriteLock {

    // 定义锁对象
    private final Object MUTEX = new Object();

    // 当前有多少个线程正在写入
    private int writingWriters = 0;

    // 当前有多少个线程正在等待写入
    private int waitingWriters = 0;

    // 当前有多少个线程正在read
    private int readingReaders = 0;

    // read 和 write 的偏好设置
    private boolean preferWriter;

    // 默认情况下preferWriter为true
    public ReadWriteLockImpl(){
        this.preferWriter = true;
    }

    // 构造ReadWriteLockImpl 并且传入preferWriter
    public ReadWriteLockImpl(boolean preferWriter){
        this.preferWriter = preferWriter;
    }

    // 创建read lock
    @Override
    public Lock readLock(){
        return new ReadLock(this);
    }

    // 创建write lock
    @Override
    public Lock writeLock(){
        return new WriteLock(this);
    }

    // 使写线程的数量增加
    void incrementWritingWriters(){
        this.writingWriters++;
    }
    // 使等待写入的线程数量增加
    void incrementWaitingWriters(){
        this.waitingWriters ++;
    }
    // 使读线程的数量增加
    void incrementReadingReaders(){
        this.readingReaders ++;
    }

    // 使写线程的数量减少
    void decrementWritingWriters(){
        this.writingWriters--;
    }
    // 使等待写入的线程数量减少
    void decrementWaitingWriters(){
        this.waitingWriters --;
    }
    // 使读线程的数量减少
    void decrementReadingReaders(){
        this.readingReaders --;
    }

    // 获取当前有多少个线程真该进行写操作
    @Override
    public int getWritingWriters() {
        return this.writingWriters;
    }

    // 获取当前有多少个线程正在等待获取写入锁
    @Override
    public int getWaitingWriters() {
        return this.getWaitingWriters();
    }
    // 获取当前有多少个线程正在进行读操作
    @Override
    public int getReadingReaders() {
        return this.getReadingReaders();
    }

    // 获取对象锁
    Object  getMutex(){
        return this.MUTEX;
    }

    // 获取当前是否偏向写锁
    boolean getPreferWriter(){
        return this.preferWriter;
    }

    // 设置写锁偏向
    void changePrefer(boolean preferWriter){
        this.preferWriter = preferWriter;
    }
}
