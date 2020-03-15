package com.majia.designpattern.singleton;

/**
 * 饿汉式 与 懒汉式结合
 */
public class SingletonObject5 {

    /**
     * volatile 修饰可以保证 可见性 有序性
     *  解决 饿汉式 与 懒汉式结合出现的空指针问题
     */
    private static volatile SingletonObject5 instance;

    public SingletonObject5() {
    }

    public static SingletonObject5 getInstance(){
        if (null == instance){
            synchronized(SingletonObject5.class){
                if(null == instance){
                    instance = new SingletonObject5();
                }
            }
        }
        return SingletonObject5.instance;
    }
}
