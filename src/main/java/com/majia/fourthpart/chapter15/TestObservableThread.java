package com.majia.fourthpart.chapter15;

import java.util.concurrent.TimeUnit;

public class TestObservableThread {
    public static void main(String[] args) {
//        Observable observableThread = new ObservableThread<>(()->{
//            try {
//                TimeUnit.SECONDS.sleep(10);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            System.out.println(" finished done.");
//            return null;
//        });
//        observableThread.start();


        final TaskLifeCycle<String> lifeCycle = new TaskLifeCycle.EmptyLifecycle<String>(){
            @Override
            public void onFinish(Thread thread, String result) {
                System.out.println("The result is " + result);
            }
        };
        Observable observableThread = new ObservableThread<>(lifeCycle,()->{
            try {
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(" finished done.");
            return "Hello Observer";
        });
        observableThread.start();
    }
}
