package com.majia.firstpart.chapter04;

import java.util.HashMap;

public class HashMapDeadLock {
    private final HashMap<String,String> map = new HashMap<>();
    public void add(String key,String value){
        this.map.put(key,value);
    }

    public static void main(String[] args) {
        final HashMapDeadLock hmdl = new HashMapDeadLock();
        for (int i = 0; i < 2; i++) {
            new Thread(()->{
                for (int j = 0; j < Integer.MAX_VALUE; j++) {
                    hmdl.add(String.valueOf(j),String.valueOf(j));
                }
            }).start();
        }
    }
}
