package com.majia.designpattern.future;

public interface Future<T> {

    T get() throws InterruptedException;
}
