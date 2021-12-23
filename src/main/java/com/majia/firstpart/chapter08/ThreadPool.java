package com.majia.firstpart.chapter08;

public interface ThreadPool {
    // 提交线程池任务
    void execute(Runnable runnable);
    // 关闭线程池
    void shutdown();
    // 获取线程池初始化大小
    int getInitSize();
    // 获取线程池最大线程数
    int getMaxSize();
    // 获取线程池的核心线程数量
    int getCoreSize();
    // 获取线程池中用于缓存任务队列的大小
    int getQueueSize();
    // 获取线程池中活跃线程的数量
    int getActiveCount();
    // 查看线程是否已经被shutdown
    boolean isShutDown();
}
