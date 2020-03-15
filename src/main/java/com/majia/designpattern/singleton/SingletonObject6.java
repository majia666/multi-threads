package com.majia.designpattern.singleton;


public class SingletonObject6 {
    /**
     *  static 初始化一次
     *
     */
    private SingletonObject6(){

    }

    private static class InstanceHolder{
        private static final SingletonObject6 instance = new SingletonObject6();
    }
    public static SingletonObject6 getInstance(){
        return InstanceHolder.instance;
    }
}
