package com.majia.thirdpart.chapter13;

public class VolatileTest {
    // 使用volatile 修饰共享资源i
    private static volatile int i = 0;

    private static void inc(){
        i++;
    }

    public static void main(String[] args) {
        for (int j = 0; j < 10; j++) {
            new Thread(()->{
                for (int k = 0; k < 1000; k++) {
                    inc();
                }
                // 使用计数器 减1

            }).start();

        }
    }
}
