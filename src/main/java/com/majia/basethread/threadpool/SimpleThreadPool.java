package com.majia.basethread.threadpool;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.IntStream;

public class SimpleThreadPool extends Thread{

    private int size;

    private final int queueSize;

    private final DiscardPolicy discardPolicy;

    //private static final int DEFAULT_SIZE = 10;

    private static final int DEFAULT_TASK_QUEUE_SIZE = 2000;

    public static final DiscardPolicy DEFAULE_DISCARD_POLICY = ()->new DiscardException("Discard This Task.");

    private static volatile int seq = 0;

    private static final ThreadGroup GROUP = new ThreadGroup("Pool_Group");

    private static final  String THREAD_PERFIX = "SIMPLE_THREAD_POOL-";

    private static final List<WorkerTask> THREAD_QUEUE = new ArrayList<WorkerTask>();

    private static final LinkedList<Runnable> TASK_QUEUE = new LinkedList<Runnable>();

    private volatile boolean destroy = false;

    private int min;

    private int max;

    private int active;

    public SimpleThreadPool() {
        this(4,8,12,DEFAULT_TASK_QUEUE_SIZE,DEFAULE_DISCARD_POLICY);
    }

    public SimpleThreadPool(int min,int active,int max,int queueSize,DiscardPolicy discardPolicy) {
        this.min = min;
        this.active = active;
        this.max = max;
        this.queueSize = queueSize;
        this.discardPolicy = discardPolicy;
        init();
    }

    public void init(){
        for (int i= 0;i< min;i++){
            createWorkTask(null);
        }
        this.size = min;
        this.start();
    }

    public void submit(Runnable runnable){
        if (destroy){
            throw  new IllegalStateException("The thread pool is destroy and not allow submit task.");
        }
        synchronized (TASK_QUEUE){
            if(TASK_QUEUE.size() > queueSize){
                discardPolicy.discard();
            }
            TASK_QUEUE.addLast(runnable);
            TASK_QUEUE.notifyAll();
        }
    }

    public void createWorkTask(String name){
        WorkerTask workerTask = new WorkerTask(GROUP, THREAD_PERFIX + (seq++));
        workerTask.start();
        TASK_QUEUE.add(workerTask);

    }
    public void shutdown() throws InterruptedException{
        while (!TASK_QUEUE.isEmpty()){
            Thread.sleep(50);
        }
        synchronized (THREAD_QUEUE){
            int initVal = THREAD_QUEUE.size();
            while (initVal > 0){
                for (WorkerTask workerTask: THREAD_QUEUE ) {
                    if (workerTask.getTaskState() == TaskState.BLOCKED){
                        workerTask.interrupt();
                        workerTask.close();
                        initVal --;
                    }else{
                        Thread.sleep(10);
                    }
                }
            }
        }

        this.destroy = true;
        System.out.println("The thread pool disposed.");
    }

    @Override
    public void run() {
        while (!destroy){
            System.out.printf("Pool#Min:%d,Active:%d,Max:%d,Current:%d,QueueSize:%d\n",this.min,this.active,this.max,this.size,TASK_QUEUE.size());
            try {
                Thread.sleep(5_000);
                if(TASK_QUEUE.size() > active && size < active){
                    for (int i = size; i < active; i++){
                        createWorkTask(null);
                    }
                    System.out.println("The pool incremented to active.");
                    size = active;
                }else if(TASK_QUEUE.size() > max && size < max){
                    for (int i = size; i < max; i++){
                        createWorkTask(null);
                    }
                    System.out.println("The pool incremented to max.");
                    size = max;
                }
                synchronized (THREAD_QUEUE) {
                    if (TASK_QUEUE.isEmpty() && size > active) {

                        int releaseSize = size - active;
                        for (Iterator<WorkerTask> it = THREAD_QUEUE.iterator(); it.hasNext(); ) {
                            if (releaseSize <= 0) {
                                break;
                            }
                            WorkerTask workerTask = it.next();
                            workerTask.close();
                            workerTask.interrupt();
                            it.remove();
                            releaseSize--;
                        }
                        size = active;
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public int getSize() {
        return size;
    }

    public int getQueueSize() {
        return queueSize;
    }

    public int getMin() {
        return min;
    }

    public int getMax() {
        return max;
    }

    public int getActive() {
        return active;
    }

    public boolean isDestroy(){
        return this.destroy;
    }
    private enum TaskState{
        FREE,RUNNING,BLOCKED,DEAD
    }

    public static class DiscardException extends RuntimeException{
        public DiscardException(String message){
            super(message);
        }
    }

    public interface DiscardPolicy{
        void discard() throws DiscardException;
    }
    private static class WorkerTask extends Thread{
        private volatile TaskState taskState = TaskState.FREE;

        public WorkerTask(ThreadGroup group,String name){
            super(group,name);
        }
        @Override
        public void run(){
            OUTER:
            while (this.taskState != TaskState.DEAD){
                Runnable runnable;
                synchronized (TASK_QUEUE){
                    while (TASK_QUEUE.isEmpty()){
                        try {
                            taskState = TaskState.BLOCKED;
                            TASK_QUEUE.wait();
                        } catch (InterruptedException e) {
                            break OUTER;
                        }

                    }
                    runnable= TASK_QUEUE.removeFirst();
                }
                if (runnable != null){
                    taskState = TaskState.RUNNING;
                    runnable.run();
                    taskState = TaskState.FREE;
                }
            }
        }
        public TaskState getTaskState(){
            return this.taskState;
        }

        public void close(){
            this.taskState = TaskState.DEAD;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        SimpleThreadPool threadPool = new SimpleThreadPool();
        IntStream.rangeClosed(0,40)
                .forEach(i->{
                    threadPool.submit(()->{
                        System.out.println("The runnable " + i + " be serviced by " + Thread.currentThread() + " start.");
                        try {
                            Thread.sleep(1_000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        System.out.println("The runnable " + i + " be serviced by " + Thread.currentThread() + " finished.");
                    });
                });
        Thread.sleep(10000);
        threadPool.shutdown();
//        threadPool.submit(()->{
//            System.out.println("===========");
//        });
    }
}
