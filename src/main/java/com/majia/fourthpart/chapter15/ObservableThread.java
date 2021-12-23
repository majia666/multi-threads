package com.majia.fourthpart.chapter15;

public class ObservableThread<T> extends Thread implements Observable {

    private final TaskLifeCycle<T> lifeCycle;

    private final Task<T> task;

    private Cycle cycle;

    //  指定Task的实现，默认情况下使用EmptyLifecycle
    public ObservableThread(Task<T> task){
        this(new TaskLifeCycle.EmptyLifecycle<>(),task);
    }

    // 指定TaskLifecycle的同时指定Task
    public ObservableThread(TaskLifeCycle<T> lifeCycle, Task<T> task) {
        super();
        // Task不允许为null
        if (task == null) {
            throw new IllegalArgumentException("The task is required.");
        }
        this.lifeCycle = lifeCycle;
        this.task = task;
    }

    @Override
    public final void run() {
        // 在执行线程逻辑单元的时候，分别触发相应的事件
        this.update(Cycle.STARTED,null,null);
        try {
            this.update(Cycle.RUNNING,null,null);
            T result = this.task.call();
            this.update(Cycle.DONE,result,null);
        }catch (Exception e){
            this.update(Cycle.ERROR,null,e);
        }


    }
    private void update(Cycle cycle,T result,Exception e){
        this.cycle = cycle;
        if (lifeCycle == null) {
            return;
        }
        try {
            switch (cycle){
                case STARTED:
                    this.lifeCycle.onStart(currentThread());
                    break;
                case RUNNING:
                    this.lifeCycle.onRunning(currentThread());
                    break;
                case DONE:
                    this.lifeCycle.onFinish(currentThread(),result);
                    break;
                case ERROR:
                    this.lifeCycle.onError(currentThread(),e);
                    break;
            }
        }catch (Exception ex){
            if (cycle == Cycle.ERROR){
                throw ex;
            }
        }

    }

    @Override
    public Cycle getCycle() {
        return this.cycle;
    }


}
