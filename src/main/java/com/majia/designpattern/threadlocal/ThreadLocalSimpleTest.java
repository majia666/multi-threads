package com.majia.designpattern.threadlocal;

public class ThreadLocalSimpleTest {

    private static ThreadLocal threadLocal = new ThreadLocal(){
        @Override
        protected Object initialValue() {
            return "12772";
        }
    };

    public static void main(String[] args) throws InterruptedException {
//        threadLocal.set("majia");
        Thread.sleep(1000);

        System.out.println(threadLocal.get());
    }
}
