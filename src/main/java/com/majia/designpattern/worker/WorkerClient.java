package com.majia.designpattern.worker;

public class WorkerClient {

    public static void main(String[] args) {

        final Channel channel = new Channel(5);

        channel.startWorker();

        new TransportThread("majia",channel).start();
        new TransportThread("hehe",channel).start();
        new TransportThread("lala",channel).start();
        new TransportThread("tata",channel).start();
    }
}
