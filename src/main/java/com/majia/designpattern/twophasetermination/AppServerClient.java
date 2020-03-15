package com.majia.designpattern.twophasetermination;

import java.io.IOException;

public class AppServerClient {

    public static void main(String[] args) {
        AppServer server = new AppServer(13345);
        server.start();

        try {
            Thread.sleep(60000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            server.shutdown();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
