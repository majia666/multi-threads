package com.majia.designpattern.singleton;

/**
 * 饿汉式
 * 不能够懒加载 线程不安全
 */
public class SingletonObject1 {

    private static final SingletonObject1 instance = new SingletonObject1();

    public SingletonObject1() {
    }

    public static SingletonObject1 getInstance(){
        return instance;
    }
}
