package com.majia.designpattern.observer;

public class OctalServer extends Observer {

    public OctalServer(Subject subject) {
        super(subject);
    }
    @Override
    public void update() {
        System.out.println("Octal String: " + Integer.toOctalString(subject.getState()));
    }
}
