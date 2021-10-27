package com.majia.firstpart.chapter01;

public class TemplateMethod {
    public final void print(String message){
        System.out.println("#######################");
        wrapPrint(message);
        System.out.println("#######################");
    }

    public void wrapPrint(String message){

    }

    public static void main(String[] args) {
        TemplateMethod t1 = new TemplateMethod() {
            @Override
            public void wrapPrint(String message) {
                System.out.println("**" + message + "**");
            }
        };
        t1.print("hello thread1");

        TemplateMethod t2 = new TemplateMethod() {
            @Override
            public void wrapPrint(String message) {
                System.out.println("**" + message + "**");
            }
        };
        t2.print("hello thread2");
    }
}
