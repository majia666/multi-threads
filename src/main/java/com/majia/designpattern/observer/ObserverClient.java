package com.majia.designpattern.observer;

public class ObserverClient {
    public static void main(String[] args) {
        final Subject subject = new Subject();
        BinaryObserver binaryObserver = new BinaryObserver(subject);
        OctalServer octalServer = new OctalServer(subject);
        System.out.println("=====================");
        subject.setState(10);
        System.out.println("=====================");
        System.out.println("=====================");
        subject.setState(15);
        System.out.println("=====================");


    }
}
