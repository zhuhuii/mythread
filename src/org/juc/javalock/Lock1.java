package org.juc.javalock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 1、公平锁 / 非公平锁（默认）
 * 2、可重入锁
 * 3、自旋锁
 * 4、死锁
 */
public class Lock1 {
    /**
     * 公平锁和非公平锁体现在别人释放锁的一瞬间，如果前面已经有排队的，新来的是否可以插队？
     *      1、如果可以插队表示是非公平的 （竞争同一个锁）
     *      2、如果不可用插队，只能排在最后面，是公平的方式。
     */
    public static void main(String[] args) {
        Lock1 instance = new Lock1();

        // 非公平锁
        instance.fairLock(false);

        // 公平锁
        // instance.fairLock(true);
    }

    /**
     * 公平/非公平锁测试
     */
    public void fairLock(boolean flag) {
        Lock lock = new ReentrantLock(flag);
        Thread t1 = new Thread(() -> {
            try {
                lock.lock();
                System.out.println("start");
                TimeUnit.SECONDS.sleep(3);  // 睡眠3s让father线程先阻塞
                show(lock, "son");
                System.out.println("end");
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();  // mian 线程释放锁，多线程才能拿到锁
            }
        });
        t1.start();

        show(lock, "father");
        System.out.println("main end! ----------");
    }

    /**
     * 模拟多线竞争同一把锁
     */
    public void show(Lock lock, String str) {
        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(() -> {
                try {
                    //System.out.println(Thread.currentThread().getName() + " ~ ready");
                    lock.lock();
                    System.out.println(Thread.currentThread().getName() + " 获取到了锁");
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            });
            thread.setName(str + "-" + i);
            thread.start();
        }
    }
}

