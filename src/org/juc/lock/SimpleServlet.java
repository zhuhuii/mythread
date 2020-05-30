package org.juc.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author zhuhui
 * @date 2020/5/23 10:52
 */
public class SimpleServlet {
    private int count;

    public int getCount() {
        return count;
    }

    Lock lock = new ReentrantLock();

    /**
     * Lock三部曲
     * 1、 new ReentrantLock();
     * 2、 lock.lock(); // 加锁
     * 3、 finally=> lock.unlock(); // 解锁
     */
    public void sale() {
        lock.lock();
        try {
            for (int i = 0; i < 10000; i++) {
                count++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 释放锁一定要在finally中
            lock.unlock();
        }
    }

    public void saleWrong() {
        lock.lock();
        for (int i = 0; i < 10000; i++) {
            count++;
        }
        int a = 1/0;
        // 释放锁一定要在finally中，否在业务异常锁一直没有释放。
        lock.unlock();
    }
}
