package com.majia.classloader.simpleclassloader;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MyClassLoaderTest {
    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {

        MyClassLoader loader = new MyClassLoader("MyClassLoader");

        Class<?> aClass = loader.loadClass("com.majia.classloader.simpleclassloader.MyObject");

        System.out.println(aClass);
        System.out.println(aClass.getClassLoader());

        Object o = aClass.newInstance();
        Method method = aClass.getMethod("hello", new Class<?>[]{});

        Object result = method.invoke(o, new Object[]{});
        System.out.println(result);
    }
}
