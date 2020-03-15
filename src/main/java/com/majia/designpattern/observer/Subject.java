package com.majia.designpattern.observer;

import java.util.ArrayList;
import java.util.List;

public class Subject {
    private List<Observer> observers = new ArrayList<Observer>();

    private int state;

    public void setState(int state) {
        if(this.state == state){
            return;
        }
        this.state = state;
        notifyObserver();
    }

    public int getState(){
        return this.state;
    }

    public void attach(Observer observer){
        observers.add(observer);
    }

    public void notifyObserver(){
        observers.stream().forEach(Observer :: update);
    }
}
