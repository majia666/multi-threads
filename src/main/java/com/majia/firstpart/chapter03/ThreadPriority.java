package com.majia.firstpart.chapter03;

public class ThreadPriority {
    public static void main(String[] args) {
       /* Thread t1 = new Thread(() -> {
            while (true){
                System.out.println("t1");
            }

        });
        t1.setPriority(3);
        Thread t2 = new Thread(() -> {
            while (true){
                System.out.println("t2");
            }

        });
        t2.setPriority(10);
        t1.start();
        t2.start();*/

     /*  // 定义一个线程组
        ThreadGroup group = new ThreadGroup("test");

        // 将线程组的优先级指定为7
        group.setMaxPriority(7);
        // 定义一个线程，将线程加入到group中
        Thread thread = new Thread(group, "test-thread");
        // 企图将线程的优先级设定为10
        thread.setPriority(10);
        // 企图未遂
        System.out.println(thread.getPriority());*/

        Thread t1 = new Thread();
        System.out.println("t1 priority " + t1.getPriority());
        Thread t2 = new Thread(() -> {
            Thread t3 = new Thread();
            System.out.println("t3 priority " + t3.getPriority());

        });
        t2.setPriority(6);
        t2.start();
        System.out.println("t2 priority " + t2.getPriority());

    }
}
