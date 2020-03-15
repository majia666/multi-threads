package com.majia.concurrentpackage.unsafe;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

public class UnsafeFooTest {


    public static void main(String[] args) throws IllegalAccessException, InstantiationException, ClassNotFoundException, NoSuchFieldException {
//        Simple simple = new Simple();
//        System.out.println(simple.getL());

//        Simple simple1 = Simple.class.newInstance();
//        Class.forName("com.majia.concurrentpackage.unsafe.UnsafeFooTest$Simple");


//        Unsafe unsafe = getUnsafe();
//        Simple simple = (Simple) unsafe.allocateInstance(Simple.class);
//        System.out.println(simple.getL());
//        System.out.println(simple.getClass());
//        System.out.println(simple.getClass().getClassLoader());
        Guard guard = new Guard();

        Field access_allowed = guard.getClass().getDeclaredField("ACCESS_ALLOWED");
        Unsafe unsafe = getUnsafe();
        unsafe.putInt(guard,unsafe.objectFieldOffset(access_allowed),42);
        guard.work();

    }

    private static Unsafe getUnsafe() {
        try {
            Field f = Unsafe.class.getDeclaredField("theUnsafe");
            f.setAccessible(true);
            return (Unsafe) f.get(null);
        } catch (Exception e) {
            throw new RuntimeException(e);
//            e.printStackTrace();
        }

    }

    static class Guard{
        private int ACCESS_ALLOWED = 1;

        private boolean allow(){
            return  42 == ACCESS_ALLOWED;
        }

        public void work(){
            if (allow()){
                System.out.println("I am working be allowed.");
            }
        }
    }

    static class Simple {
        private long l = 0;

        public Simple() {
            this.l = 1;
            System.out.println("=====");
        }

        public long getL() {
            return l;
        }
    }
}
