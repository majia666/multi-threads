package com.majia.concurrentpackage.unsafe;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

public class UnsafeTest {

    public static void main(String[] args) {
        //Unsafe unsafe = Unsafe.getUnsafe();
        Unsafe unsafe = getUnsafe();
        System.out.println(unsafe);
    }

    private static Unsafe getUnsafe(){
        try {
            Field f = Unsafe.class.getDeclaredField("theUnsafe");
            f.setAccessible(true);
            return (Unsafe)f.get(null);
        } catch (Exception e) {
            throw new RuntimeException(e);
//            e.printStackTrace();
        }

    }
}
