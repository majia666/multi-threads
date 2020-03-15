package com.majia.designpattern.observer;

import java.util.Arrays;

public class ThreadCycleClient {
    public static void main(String[] args) {
        new ThreadLifeCycleobserver().concurrentQuery(Arrays.asList("1","2"));
    }
}
