package org.juc.CAS;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * 原子引用，和 mysql 乐观锁原理一样
 */
public class AtomicRefence {

    private static AtomicStampedReference<Integer> atomicStampedReference = new AtomicStampedReference(1, 1);

    public static void main(String[] args) {
        new Thread(() -> {
            System.out.println("a1 => " + atomicStampedReference.getStamp());

            try {
                // 模拟A线程速度快先执行
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("a1修改 => " + atomicStampedReference.compareAndSet(1, 2,
                    atomicStampedReference.getStamp(), atomicStampedReference.getStamp() + 1));

            System.out.println("a2 => " + atomicStampedReference.getStamp());
            System.out.println("a2修改 => " + atomicStampedReference.compareAndSet(2, 1,
                    atomicStampedReference.getStamp(), atomicStampedReference.getStamp() + 1));
        }, "A").start();

        new Thread(() -> {
            int stamp = atomicStampedReference.getStamp();
            System.out.println("b1 => " + stamp);

            try {
                // 模拟A线程速度快先执行
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("b1修改 => " + atomicStampedReference.compareAndSet(1, 6,
                    stamp, stamp + 1));
        }, "B").start();

        while (Thread.activeCount() > 2) {     // main gc 两个线程
            Thread.yield();
        }
        System.out.println(atomicStampedReference.getReference());
    }
}
