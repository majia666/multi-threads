package com.majia.firstpart.chapter06;

public class ThreadGroupCreator {
    public static void main(String[] args) {
        // ①获取当前线程的 group
        ThreadGroup currentGroup= Thread.currentThread().getThreadGroup();
        // ②定义一个新的group
        ThreadGroup group1 = new ThreadGroup("Group1");
        // ③程序输出true
        System.out.println(group1.getParent() == currentGroup);
        // ④定义group2，指定group1为group
        ThreadGroup group2 = new ThreadGroup(group1, "Group2");
        // ⑤程序输出true
        System.out.println(group2.getParent() == group1);
    }
}
