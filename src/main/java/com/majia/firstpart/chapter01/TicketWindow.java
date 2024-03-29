package com.majia.firstpart.chapter01;

public class TicketWindow extends Thread {

    private final String name;
    private static int MAX = 50000;
    private static int index = 1;
    public TicketWindow(String name){
        this.name = name;
    }
    @Override
    public void run() {
        while (index <= MAX){
            System.out.println("柜台：" + name + " 当前号码是：" + (index++));
        }
    }

    public static void main(String[] args) {
        TicketWindow ticketWindow1 = new TicketWindow("一号出号机");
        ticketWindow1.start();
        TicketWindow ticketWindow2 = new TicketWindow("二号出号机");
        ticketWindow2.start();
        TicketWindow ticketWindow3 = new TicketWindow("三号出号机");
        ticketWindow3.start();
        TicketWindow ticketWindow4 = new TicketWindow("四号出号机");
        ticketWindow4.start();
    }
}
