package com.majia.designpattern.twophasetermination;

import java.io.*;
import java.net.Socket;

public class ClientHandler implements Runnable {

    private final Socket socket;

    private volatile boolean running = true;

    public ClientHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try (InputStream inputStream = socket.getInputStream();
             OutputStream outputStream = socket.getOutputStream();
             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
             PrintWriter writer = new PrintWriter(outputStream)) {
            while (running) {
                String message = reader.readLine();
                if (null == message || "".equals(message)){
                    break;
                }
                System.out.println("Come from client -> " + message);
                writer.write("echo "+ message +"\n");
                writer.flush();
            }
        } catch (IOException e) {
            this.running = false;
            e.printStackTrace();
        }finally {
            this.stop();
        }

    }

    public void stop() {
        if (!running){
            return;
        }
        this.running = false;
        try {
            this.socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
