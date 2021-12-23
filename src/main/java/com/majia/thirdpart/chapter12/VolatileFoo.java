package com.majia.thirdpart.chapter12;

import java.util.concurrent.TimeUnit;

public class VolatileFoo {
    // init_value 的最大值
    final static int MAX = 5;
    // init_value 的初始值
    static  volatile int init_value = 0;

    public static void main(String[] args) {
        // 启动一个Reader 线程，当发现local_value和init_value不同时，则输出init_value被修改的信息
        new Thread(()->{
            int localValue = init_value;
            while (localValue < MAX){
                if (init_value != localValue){
                    System.out.printf("The init_value is updated to [%d]\n",init_value);
                    // 对localValue进行重新赋值
                    localValue = init_value;
                }
            }
        },"Reader").start();

        // 启动一个Updater线程,主要用于对init_value 的修改，当localValue >= 5 的时候则退出生命周期 511621199812280068
        new Thread(()->{
            int localValue = init_value;
            while (localValue < MAX){
                System.out.printf("The init_value will be changed to [%d]\n", ++localValue);
                init_value = localValue;
                try {
                    // 短暂休眠，目的是为了使Reader线程能够来得及输出变化的内容
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"Updater").start();
    }
}
