package com.majia.designpattern.threadpermessage;

import java.util.stream.IntStream;

public class PerThreadClient {

    public static void main(String[] args) {
        MessageHandler handler = new MessageHandler();
        IntStream.rangeClosed(0, 10).forEach(i -> {
            handler.request(new Message(String.valueOf(i)));

        });

        handler.shutdown();
    }
}
