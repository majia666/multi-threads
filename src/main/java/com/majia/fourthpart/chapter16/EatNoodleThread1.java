package com.majia.fourthpart.chapter16;

public class EatNoodleThread1 extends Thread{
    private final String name;

    private final TableWarePair tableWarePair;

    public EatNoodleThread1(String name, TableWarePair tableWarePair) {
        this.name = name;
        this.tableWarePair = tableWarePair;
    }

    @Override
    public void run() {
        while (true){
            this.eat();
        }
    }
    // 吃面条的过程
    private void eat(){
        synchronized (tableWarePair){
            System.out.println(name + " take up " + tableWarePair.getLeftTool() + "(left)");
            System.out.println(name + " take up " + tableWarePair.getRightTool() + "(right)");
            System.out.println(name + " is eating now.");
            System.out.println(name + " put down " + tableWarePair.getRightTool() + "(right)");
            System.out.println(name + " put down " + tableWarePair.getLeftTool()  + "(left)");
        }
    }

    public static void main(String[] args) {
        TableWare fork = new TableWare("fork");
        TableWare knife = new TableWare("knife");
        TableWarePair tableWarePair = new TableWarePair(fork, knife);
        new EatNoodleThread1("A",tableWarePair).start();
        new EatNoodleThread1("B",tableWarePair).start();
    }
}
