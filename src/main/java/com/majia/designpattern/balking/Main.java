package com.majia.designpattern.balking;

public class Main {

    public static void main(String[] args) {
        BalkingData data = new BalkingData("balking-data.txt", "testbalking");
        new ChangerThread("ChangerThread",data).start();
        new ServerThread("SaverThread",data).start();
    }
}
