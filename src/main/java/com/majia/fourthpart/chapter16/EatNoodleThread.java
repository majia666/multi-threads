package com.majia.fourthpart.chapter16;

public class EatNoodleThread extends Thread{
    private final String name;
    // 左手边的工具
    private final TableWare leftTool;
    // 右手边的工具
    private final TableWare rightTool;

    public EatNoodleThread(String name, TableWare leftTool, TableWare rightTool) {
        this.name = name;
        this.leftTool = leftTool;
        this.rightTool = rightTool;
    }

    @Override
    public void run() {
        while (true){
            this.eat();
        }
    }
    // 吃面条的过程
    private void eat(){
        synchronized (leftTool){
            System.out.println(name + " take up " + leftTool + "(left)");
            synchronized (rightTool){
                System.out.println(name + " take up " + rightTool + "(right)");
                System.out.println(name + " is eating now.");
                System.out.println(name + " put down " + rightTool + "(right)");
            }
            System.out.println(name + " put down " + leftTool + "(left)");
        }
    }

    public static void main(String[] args) {
        TableWare fork = new TableWare("fork");
        TableWare knife = new TableWare("knife");
        new EatNoodleThread("A",fork,knife).start();
        new EatNoodleThread("B",fork,knife).start();
    }
}
