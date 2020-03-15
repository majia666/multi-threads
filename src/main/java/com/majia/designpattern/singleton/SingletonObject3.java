package com.majia.designpattern.singleton;

/**
 *  可以懒加载，并且是一个实例，但是成为串行，影响效率
 *
 */
public class SingletonObject3 {

    private static SingletonObject3 instance;

    public SingletonObject3() {
    }

    public synchronized static SingletonObject3 getInstance(){
        if(null == instance){
            instance = new SingletonObject3();
        }
        return SingletonObject3.instance;
    }
}
