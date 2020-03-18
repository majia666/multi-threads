package com.majia.test.testimmutable;

import java.util.stream.IntStream;

public class TestImmutable {

    public static void main(String[] args) {
        IntStream.range(0,100).forEach(i->{
            new PrintPersonThread(new Person("Alice","Arfef")).start();
        });
    }
}
