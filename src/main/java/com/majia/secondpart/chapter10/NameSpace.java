package com.majia.secondpart.chapter10;

public class NameSpace {
    public static void main(String[] args) throws ClassNotFoundException {
        // 获取系统类加载器
       /* ClassLoader classLoader = NameSpace.class.getClassLoader();
        Class<?> aClass = classLoader.loadClass("com.majia.secondpart.chapter10.Test");
        Class<?> bClass = classLoader.loadClass("com.majia.secondpart.chapter10.Test");
        System.out.println(aClass.hashCode());
        System.out.println(bClass.hashCode());
        System.out.println(aClass == bClass);
*/
        // ① 不同类加载器加载同一个class
        MyClassLoader classLoader1 = new MyClassLoader("E:\\classloader1", null);
        BrokerDelegateClassLoader classLoader2 = new BrokerDelegateClassLoader("E:\\classloader1", null);
        Class<?> aClass = classLoader1.loadClass("com.majia.secondpart.chapter10.HelloWorld");
        Class<?> bClass = classLoader2.loadClass("com.majia.secondpart.chapter10.HelloWorld");
        System.out.println(aClass.getClassLoader());
        System.out.println(bClass.getClassLoader());
        System.out.println(aClass.hashCode());
        System.out.println(bClass.hashCode());
        System.out.println(aClass == bClass);

        // 相同的类加载器加载同一个class
        /*MyClassLoader classLoader1 = new MyClassLoader("E:\\classloader1", null);
        MyClassLoader classLoader2 = new MyClassLoader("E:\\classloader1", null);
        Class<?> aClass = classLoader1.loadClass("com.majia.secondpart.chapter10.HelloWorld");
        Class<?> bClass = classLoader2.loadClass("com.majia.secondpart.chapter10.HelloWorld");
        System.out.println(aClass.getClassLoader());
        System.out.println(bClass.getClassLoader());
        System.out.println(aClass.hashCode());
        System.out.println(bClass.hashCode());
        System.out.println(aClass == bClass);*/
    }
}
