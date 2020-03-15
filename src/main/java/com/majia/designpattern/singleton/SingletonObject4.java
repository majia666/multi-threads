package com.majia.designpattern.singleton;

/**
 *  饿汉式 懒汉式 结合
 *  但是可能引起空指针异常
 *
 */
public class SingletonObject4 {

    private static SingletonObject4 instance;

    public SingletonObject4() {
    }

    public static SingletonObject4 getInstance(){
        if (null == instance){
            synchronized(SingletonObject4.class){
                if(null == instance){
                    instance = new SingletonObject4();
                }
            }
        }
        return SingletonObject4.instance;
    }
}
