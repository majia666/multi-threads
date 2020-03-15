package com.majia.designpattern.activeobjects;

import java.util.LinkedList;

public class ActivationQueue {

    private static final int MAX_METHOD_REQUEST_QUEUE_SIZE = 100;

    private final LinkedList<MethodRequest> methodQueue;

    public ActivationQueue() {

        methodQueue = new LinkedList<MethodRequest>();
    }

    public synchronized void put(MethodRequest methodRequest) {

        while (methodQueue.size() >= MAX_METHOD_REQUEST_QUEUE_SIZE) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.methodQueue.addLast(methodRequest);
        this.notifyAll();
    }

    public synchronized MethodRequest take() {
        while (methodQueue.isEmpty()) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        MethodRequest methodRequest = methodQueue.removeFirst();
        this.notifyAll();
        return methodRequest;
    }
}
