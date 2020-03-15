package com.majia.classloader.codecclassloader;

import com.majia.classloader.simpleclassloader.MyClassLoader;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ClassLoaderTest {

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {

        DecryptClassLoader myClassLoader = new DecryptClassLoader();

        myClassLoader.setDir("E:\\testclassloader\\app\\classloader3");

        Class<?> aClass = myClassLoader.loadClass("com.majia.classloader.simpleclassloader.MyObject");

        System.out.println(aClass);

        System.out.println(aClass.getClassLoader());

        Object o = aClass.newInstance();
        Method method = aClass.getMethod("hello", new Class<?>[]{});

        Object result = method.invoke(o, new Object[]{});
        System.out.println(result);

    }
}
