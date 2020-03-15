package com.majia.concurrentpackage.atomic.integer;

public class AtomicIntegerDetailsTest2 {

    private static final CompareAndSetLock lock = new CompareAndSetLock();

    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            new Thread(()->{
                try {
                    //doSomething();
                    doSomething2();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (GetLockException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }

    public static void doSomething() throws InterruptedException {
        synchronized (AtomicIntegerDetailsTest2.class){
            System.out.println(Thread.currentThread().getName() + " get the lock");

            Thread.sleep(1000);
        }
    }

    public static void doSomething2() throws InterruptedException, GetLockException {

        try {
            synchronized (lock){
                lock.tryLock();
                System.out.println(Thread.currentThread().getName() + " get the lock");

                Thread.sleep(1000);
            }
        }finally {
            lock.unlock();
        }

    }
}
