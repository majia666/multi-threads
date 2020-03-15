package com.majia.designpattern.observer;

public interface LifeCycleListener {
    public void onEvent(ObservableRunnable.RunnableEvent event);
}
