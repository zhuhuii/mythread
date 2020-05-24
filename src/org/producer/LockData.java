package org.producer;

import com.sun.org.apache.bcel.internal.generic.NEW;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 生产者消费者模式
 */
public class LockData {
    Lock lock = new ReentrantLock();
    Condition condition = lock.newCondition();


    //判断等待，业务，通知
    private int num = 0;

    /**
     * +1操作
     */
    public synchronized void increment() {
        lock.lock();
        while (num != 0) {
            // 等待
            try {
                condition.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        num++;
        System.out.println(Thread.currentThread().getName() + "==>" + num);
        //通知
        condition.signalAll();
        lock.unlock();
    }

    /**
     * -1操作
     */
    public synchronized void decrement() {
        lock.lock();
        while (num != 1) {
            // 等待
            try {
                condition.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        num--;
        System.out.println(Thread.currentThread().getName() + "==>" + num);
        //通知
        condition.signalAll();
    }
}
