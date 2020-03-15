package com.majia.classloader.breakparentclassloader;

public class SimpleClassLoaderTest {

    public static void main(String[] args) throws ClassNotFoundException {

        SimpleClassLoader simpleClassLoader = new SimpleClassLoader();

        Class<?> aClass = simpleClassLoader.loadClass("com.majia.classloader.breakparentclassloader.SimpleObject");

        System.out.println(aClass.getClassLoader());
    }
}
