package org.juc.threadlocal;

import java.util.Map;

/**
 * 每个线程提供一个局部的变量副本,实现了线程之间的数据隔离
 */
public class MyThreadLocal {
    private static ThreadLocal<String> stringThreadLocal = new ThreadLocal<>();

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            System.out.println(stringThreadLocal.get());
            stringThreadLocal.set("zhangSan");
            System.out.println(stringThreadLocal.get());
        }, "A");

        Thread t2 = new Thread(() -> {
            String threadName = Thread.currentThread().getName();
            System.out.println(threadName + " => " + stringThreadLocal.get());
            stringThreadLocal.set("lisi");
            System.out.println(threadName + " => " + stringThreadLocal.get());
            stringThreadLocal.remove();
            System.out.println(threadName + " => " + stringThreadLocal.get());
        }, "B");

        t1.start();
        t1.join();
        t2.start();
    }
}
