package org.producer;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * LockCondition 监视器
 */
public class LockCondition {
    // 1=A  2=B  3=C
    private int num = 1;
    private Lock lock = new ReentrantLock();
    // condition 精准通知
    private Condition condition1 = lock.newCondition();
    private Condition condition2 = lock.newCondition();
    private Condition condition3 = lock.newCondition();

    public void printA() throws InterruptedException {
        lock.lock();
        while (num != 1) {
            // 等待
            condition1.await();
        }
        // 业务代码
        System.out.println(Thread.currentThread().getName() + "==>AAA");
        num = 2;
        // 通知
        condition2.signal();
        lock.unlock();
    }

    public void printB() throws InterruptedException {
        lock.lock();
        while (num != 2) {
            // 等待
            condition2.await();
        }
        // 业务代码
        System.out.println(Thread.currentThread().getName() + "==>BBB");
        num = 3;
        // 通知
        condition3.signal();
        lock.unlock();
    }

    public void printC() throws InterruptedException {
        lock.lock();
        while (num != 3) {
            // 等待
            condition3.await();
        }
        // 业务代码
        System.out.println(Thread.currentThread().getName() + "==>CCC");
        num = 1;
        // 通知
        condition1.signal();
        lock.unlock();
    }
}
