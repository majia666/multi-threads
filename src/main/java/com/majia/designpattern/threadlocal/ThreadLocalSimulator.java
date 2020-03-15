package com.majia.designpattern.threadlocal;

import java.util.HashMap;
import java.util.Map;

public class ThreadLocalSimulator<T> {

    private final Map<Thread, T> storage = new HashMap<Thread, T>();

    public void set(T t) {
        synchronized (this) {
            Thread key = Thread.currentThread();
            storage.put(key, t);
        }
    }

    public T get() {
        synchronized (this) {
            Thread key = Thread.currentThread();
            T value = storage.get(key);
            if (value == null) {
                return initialValue();
            } else {
                return value;
            }
        }
    }

    public T initialValue(){
        return null;
    }
}
