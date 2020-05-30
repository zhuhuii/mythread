package org.juc.volatiles;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 原子类
 */
public class Atomic {
    // volatile 不保证原子性
    // 原子类的 Integer
    private volatile static AtomicInteger num = new AtomicInteger();

    public static void main(String[] args) {
        //理论上num结果应该为 50 万
        for (int i = 1; i <= 50; i++) {
            new Thread(() -> {
                for (int j = 1; j <= 10000; j++) {
                    // num++; 不是一个原子性操作
                    num.getAndIncrement();  // AtomicInteger + 1 CAS
                }
            }).start();
        }

        while (Thread.activeCount() > 2) {  // main线程， gc线程
            Thread.yield();
        }

        System.out.println(Thread.currentThread().getName() + " " + num);
    }
}
