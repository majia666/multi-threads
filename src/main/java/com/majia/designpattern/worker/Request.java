package com.majia.designpattern.worker;

public class Request {

    private final String name;

    private final int number;

    public Request(final String name, final int number) {
        this.name = name;
        this.number = number;
    }

    public void execute(){
        System.out.println(Thread.currentThread().getName() + " executed " + this.toString());
    }

    @Override
    public String toString() {
        return "Request-> No. " + number + " Name. " + name;
    }
}
