package com.majia.designpattern.immutable;

public final class Person {
    private final String name;
    private final String adderss;

    public Person(String name, String adderss) {
        this.name = name;
        this.adderss = adderss;
    }

    public String getName() {
        return name;
    }

    public String getAdderss() {
        return adderss;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", adderss='" + adderss + '\'' +
                '}';
    }
}
