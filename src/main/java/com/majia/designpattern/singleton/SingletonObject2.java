package com.majia.designpattern.singleton;

/**
 * 懒汉式
 * 线程安全 但是可能会创建多个实例
 */
public class SingletonObject2 {

    private static SingletonObject2 instance;

    public SingletonObject2() {
    }

    public static SingletonObject2 getInstance(){
        if(null == instance){
            instance = new SingletonObject2();
        }
        return SingletonObject2.instance;
    }
}
