package com.majia.concurrentpackage.atomic.atomicboolean;

import java.util.concurrent.atomic.AtomicBoolean;

public class AtomicBooleanTest {

    public static void main(String[] args) {
//        createWithoutArguments();
//        createWithArguments();
        AtomicBoolean aBoolean = new AtomicBoolean();
        boolean b = aBoolean.compareAndSet(true, false);
        System.out.println(b);

    }

    public static void createWithoutArguments(){
        AtomicBoolean aBoolean = new AtomicBoolean();
        boolean b = aBoolean.get();
        System.out.println(b);

    }

    public static void createWithArguments(){
        AtomicBoolean aBoolean = new AtomicBoolean(true);
        boolean b = aBoolean.get();
        System.out.println(b);

    }
}
