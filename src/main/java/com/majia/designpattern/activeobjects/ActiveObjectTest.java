package com.majia.designpattern.activeobjects;

public class ActiveObjectTest {

    public static void main(String[] args) {
        ActiveObject activeObject = ActiveObjectFactory.createActiveObject();

        new MakeClientThread("majia",activeObject).start();
        new MakeClientThread("lala",activeObject).start();

        new DisplayClientThread("haha",activeObject).start();
//        new DisplayClientThread("caca",activeObject).start();
    }
}
