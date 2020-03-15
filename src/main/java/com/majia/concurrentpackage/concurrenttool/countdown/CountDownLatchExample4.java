package com.majia.concurrentpackage.concurrenttool.countdown;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CountDownLatchExample4 {

    private static Random random = new Random(System.currentTimeMillis());

    static class Event {
        int id = 0;

        public Event(int id) {
            this.id = id;
        }
    }

    interface Watcher {
        void done(Table table);
    }

    static class TaskBatch implements Watcher {

        private CountDownLatch countDownLatch;

        private TaskGroup taskGroup;

        public TaskBatch(TaskGroup taskGroup,int size) {
            this.taskGroup = taskGroup;
            this.countDownLatch = new CountDownLatch(size);
        }

        @Override
        public void done(Table table) {
            countDownLatch.countDown();
            if (countDownLatch.getCount() == 0) {
                System.out.println("The table " + table.tableName + " finished work,[" + table + "]");
                taskGroup.done(table);
            }

        }
    }
    static class TaskGroup implements Watcher {

        private CountDownLatch countDownLatch;

        private Event event;

        public TaskGroup(int size,Event event) {
            this.countDownLatch = new CountDownLatch(size);
            this.event = event;
        }

        @Override
        public void done(Table table) {
            countDownLatch.countDown();
            if (countDownLatch.getCount() == 0) {
                System.out.println("====All table done in event " + event.id );
            }

        }
    }
    static class Table {
        String tableName;
        long sourceRecordCount = 10;
        long targetCount;
        String sourceColumnSchema = "<table name='a'><column name='coll' type='varchar2'/></table>";
        String targetColumnSchema = "";

        public Table(String tableName, long sourceRecordCount, long targetCount) {
            this.tableName = tableName;
            this.sourceRecordCount = sourceRecordCount;
            this.targetCount = targetCount;
        }

        @Override
        public String toString() {
            return "Table{" +
                    "tableName='" + tableName + '\'' +
                    ", sourceRecordCount=" + sourceRecordCount +
                    ", targetCount=" + targetCount +
                    ", sourceColumnSchema='" + sourceColumnSchema + '\'' +
                    ", targetColumnSchema='" + targetColumnSchema + '\'' +
                    '}';
        }
    }

    static class TrustSourceRecordCount implements Runnable {

        private final Table table;
        private final TaskBatch taskBatch;

        public TrustSourceRecordCount(Table table, TaskBatch taskBatch) {
            this.table = table;
            this.taskBatch = taskBatch;
        }
        @Override
        public void run() {
            try {
                Thread.sleep(random.nextInt(1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            table.targetCount = table.sourceRecordCount;
            taskBatch.done(table);
//            System.out.println("The table " + table.tableName + " target record count capture done and update the data.");
        }
    }

    static class TrustSourceRecordColumns implements Runnable {
        private final Table table;
        private final TaskBatch taskBatch;
        public TrustSourceRecordColumns(Table table, TaskBatch taskBatch) {
            this.table = table;
            this.taskBatch = taskBatch;
        }

        @Override
        public void run() {
            try {
                Thread.sleep(random.nextInt(1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            table.targetColumnSchema = table.sourceColumnSchema;
            taskBatch.done(table);
//            System.out.println("The table " + table.tableName + " target columns capture done and update the data.");
        }
    }

    private static List<Table> capture(Event event) {
        List<Table> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(new Table("table-" + event.id + "-" + i, i * 1000, 0));
        }
        return list;
    }


    public static void main(String[] args) {

        Event[] events = {new Event(1), new Event(2)};
        ExecutorService executor = Executors.newFixedThreadPool(5);
        for (Event event : events) {

            List<Table> list = capture(event);
            TaskGroup taskGroup = new TaskGroup(list.size(),event);
            for (Table table : list) {
                TaskBatch taskBatch = new TaskBatch(taskGroup,2);
                TrustSourceRecordColumns columnsRunnable = new TrustSourceRecordColumns(table, taskBatch);
                TrustSourceRecordCount countRunnable = new TrustSourceRecordCount(table, taskBatch);
                executor.submit(columnsRunnable);
                executor.submit(countRunnable);
            }
        }
    }
}
