package com.majia.firstpart.chapter02;

import java.util.stream.IntStream;

public class TestThreadGroup {
    public static void main(String[] args) {
        IntStream.range(0,5).boxed().map(
                integer -> new Thread(
                        ()-> System.out.println(Thread.currentThread().getName()))
        ).forEach(Thread::start);

    }
}
