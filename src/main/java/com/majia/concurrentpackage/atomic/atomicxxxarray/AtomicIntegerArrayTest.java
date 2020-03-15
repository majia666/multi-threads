package com.majia.concurrentpackage.atomic.atomicxxxarray;

import java.util.concurrent.atomic.AtomicIntegerArray;

public class AtomicIntegerArrayTest {


    public static void main(String[] args) {
//        System.out.println(createAtomicIntegerArray().length());
//        System.out.println(createAtomicIntegerArray().get(5));
        int andSet = createAtomicIntegerArray().getAndSet(0, 10);
        System.out.println(andSet);
        System.out.println(createAtomicIntegerArray().get(5));
    }

    public static AtomicIntegerArray createAtomicIntegerArray(){
        AtomicIntegerArray integerArray = new AtomicIntegerArray(10);
        return integerArray;
    }
}
