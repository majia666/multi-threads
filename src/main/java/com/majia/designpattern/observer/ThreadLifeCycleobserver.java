package com.majia.designpattern.observer;

import java.util.List;

public class ThreadLifeCycleobserver implements LifeCycleListener {

    private static final Object LOCK = new Object();

    public void concurrentQuery(List<String> ids){
        if (ids == null || ids.isEmpty()){
            return;
        }
        ids.stream().forEach(id -> {
            new Thread(new ObservableRunnable(this) {
                @Override
                public void run() {
                    try {
                        notifyChange(new RunnableEvent(RunnableState.RUNNING, Thread.currentThread(), null));
                        System.out.println("query for the id " + id);
                        Thread.sleep(1000);
                        notifyChange(new RunnableEvent(RunnableState.DONE, Thread.currentThread(), null));
                    } catch (Exception e) {
                        notifyChange(new RunnableEvent(RunnableState.ERROR, Thread.currentThread(), e));

                    }

                }
            }
                    , id).start();
        });
    }

    @Override
    public void onEvent(ObservableRunnable.RunnableEvent event) {
        synchronized (LOCK){
            System.out.println("The runnable [" + event.getThread().getName() +"] data changed state is " + event.getRunableState());
            if (event.getCause() != null){
                System.out.println("The runnable [" + event.getThread().getName() +"] process failed.");
                event.getCause().printStackTrace();

            }
        }
    }
}
