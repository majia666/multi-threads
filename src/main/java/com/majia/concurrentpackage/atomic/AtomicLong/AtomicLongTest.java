package com.majia.concurrentpackage.atomic.AtomicLong;

import java.util.concurrent.atomic.AtomicLong;

public class AtomicLongTest {

    public static void main(String[] args) {
        System.out.println(createAtomicLong());
    }

    public static Long createAtomicLong(){
        AtomicLong atomicLong = new AtomicLong(100L);
        return atomicLong.get();
    }
}
