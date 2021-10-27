package com.majia.firstpart.chapter02;

import java.util.stream.IntStream;

public class ThreadConstruction {

    private final static String PREFIX = "MAJIA-";

    public static void main(String[] args) {
//        IntStream.range(0,5).mapToObj(ThreadConstruction::createThread).forEach(Thread::start);// 创建线程的时候给线程起名字
        // 测试TheadGroup
//        Thread t1 = new Thread("t1");
//        ThreadGroup testGroup = new ThreadGroup("TestGroup");
//        Thread t2 = new Thread(testGroup, "t2");
//        ThreadGroup mainThreadGroup = Thread.currentThread().getThreadGroup();
//
//        System.out.println("Main Thread belong group: " + mainThreadGroup);
//        System.out.println("t1 and main thread belong the same group: " + (mainThreadGroup == t1.getThreadGroup()));
//        System.out.println("t2 and main thread not belong the  main group: " + (mainThreadGroup == t2.getThreadGroup()));
//        System.out.println("t2 and main thread belong the testGroup: " + (testGroup == t2.getThreadGroup()));
        if (args.length<1){
            System.out.println("Please enter the stack size.");
            System.exit(1);
        }
        ThreadGroup group = new ThreadGroup("TestGroup");
        Runnable runnable = new Runnable() {
            final int MAX = Integer.MAX_VALUE;
            @Override
            public void run() {
                int i = 0;
                recurse(i);
            }
            private void recurse(int i){
                System.out.println(i);
                if (i<MAX){
                    recurse(i + 1);
                }
            }
        };

        Thread thread = new Thread(group,runnable,"Test",Integer.parseInt(args[0]));
        thread.start();
    }

    private static Thread createThread(final int intName){
        return new Thread(()-> System.out.println(Thread.currentThread().getName()),PREFIX + intName);
    }
}
