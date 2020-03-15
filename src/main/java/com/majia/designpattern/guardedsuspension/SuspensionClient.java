package com.majia.designpattern.guardedsuspension;

public class SuspensionClient {
    public static void main(String[] args) {
        final RequestQueue queue = new RequestQueue();
        new ClientThread(queue, "majia").start();
        ServerThread serverThread = new ServerThread(queue);
        serverThread.start();

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        serverThread.close();
    }
}
