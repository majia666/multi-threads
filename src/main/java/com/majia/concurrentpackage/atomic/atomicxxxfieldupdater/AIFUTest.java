package com.majia.concurrentpackage.atomic.atomicxxxfieldupdater;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

public class AIFUTest {

    private volatile int i;

    private AtomicIntegerFieldUpdater<AIFUTest> updater = AtomicIntegerFieldUpdater.newUpdater(AIFUTest.class, "i");

    public void update(int newValue) {
        updater.compareAndSet(this, i, newValue);
    }

    public int getI() {
        return this.i;
    }

    public static void main(String[] args) {
        AIFUTest aifuTest = new AIFUTest();

        aifuTest.update(10);

        System.out.println(aifuTest.getI());
    }
}
