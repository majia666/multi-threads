package com.majia.designpattern.immutable;

import java.util.stream.IntStream;

/**
 * 多线程不可变对象设计模式
 */
public class ImmutableClient {
    public static void main(String[] args) {

        Person person = new Person("majia", "henan");

        IntStream.range(0,5).forEach(i->{
            new UsePersonThread(person).start();
        });
    }
}
