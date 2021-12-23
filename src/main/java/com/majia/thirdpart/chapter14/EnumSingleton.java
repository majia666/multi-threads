package com.majia.thirdpart.chapter14;

/**
 * 6.枚举方式
 */
// 枚举类型本身是final的，不允许被继承
public enum  EnumSingleton {

    INSTANCE;
    // 实例变量
    private byte[] data = new byte[1024];

    EnumSingleton(){
        System.out.println("INSTANCE will be initialized immediately");
    }

    public static void method(){
        // 调用该方法则会主动使用Singleton,INSTANCE将会被实例化
    }
    public static EnumSingleton getInstance(){
        return INSTANCE;
    }
}
