package com.majia.designpattern.observer;

public abstract class ObservableRunnable implements Runnable {
  protected  LifeCycleListener listener;

    public ObservableRunnable(final LifeCycleListener listener) {
        this.listener = listener;
    }

    public void notifyChange(final RunnableEvent event){
        listener.onEvent(event);
    }
    public enum RunnableState{
        RUNNING,ERROR,DONE
    }

    public static class RunnableEvent{
        private final RunnableState runableState;
        private final Thread thread;
        private final Throwable cause;

        public RunnableEvent(RunnableState runableState, Thread thread, Throwable cause) {
            this.runableState = runableState;
            this.thread = thread;
            this.cause = cause;
        }

        public RunnableState getRunableState() {
            return runableState;
        }

        public Thread getThread() {
            return thread;
        }

        public Throwable getCause() {
            return cause;
        }
    }
}
