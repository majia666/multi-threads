package com.majia.concurrentpackage.executors;

import java.util.Timer;
import java.util.TimerTask;

public class TimerScheduler {

    /**
     * 定时任务
     * 1.Timer/ TimerTask
     * 2.ScheduleExecutorService
     * 3.crontab
     * 4.cron4j
     * 5.quartz
     * @param args
     */
    public static void main(String[] args) {
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                System.out.println("=========" + System.currentTimeMillis());
            }
        };
        timer.schedule(task,1000,1000);
    }
}
