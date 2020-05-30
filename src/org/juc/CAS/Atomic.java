package org.juc.CAS;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 原子类
 */
public class Atomic {
    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger(2020);
        // 比较并交换
        System.out.println(atomicInteger.compareAndSet(2020, 666));
        System.out.println(atomicInteger);

        System.out.println(atomicInteger.compareAndSet(666, 2020));
        System.out.println(atomicInteger);

        System.out.println("=============ABA问题================");
        ABA();
    }

    /**
     * ABA问题
     */
    public static void ABA() {
        AtomicInteger atomicInteger = new AtomicInteger(100);

        new Thread(()->{
            System.out.println(atomicInteger);
            atomicInteger.compareAndSet(100,666);
            atomicInteger.compareAndSet(666,100);

        },"A").start();

        new Thread(()->{
            System.out.println(atomicInteger);
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // 我修改之前被其他速度快的线程修改过了（狸猫换太子）
            atomicInteger.compareAndSet(atomicInteger.get(), 500);
        },"B").start();

        while (Thread.activeCount()>2){     // main gc 两个线程
            Thread.yield();
        }
        System.out.println(atomicInteger);
    }
}
