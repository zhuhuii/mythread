package org.javalock;

import java.util.concurrent.TimeUnit;

/**
 * 死锁
 */
public class Lock4 {
    public static void main(String[] args) {
        String lockA = "lockA";
        String lockB = "lockB";
        new Thread(new MyDeadLock(lockA, lockB), "T1").start();
        new Thread(new MyDeadLock(lockB, lockA), "T2").start();
    }
}

/**
 * 解决问题
 * 1、使用 jps -l 定位进程号
 * 2、使用 jstack 进程号 找到死锁问题
 * <p>
 * 面试，工作中！ 排查问题：
 * 1、日志
 * 2、堆栈
 */
class MyDeadLock implements Runnable {

    private String lockA;
    private String lockB;

    public MyDeadLock(String lockA, String lockB) {
        this.lockA = lockA;
        this.lockB = lockB;
    }

    @Override
    public void run() {
        synchronized (lockA) {
            System.out.println(Thread.currentThread().getName() +
                    "lock:" + lockA + "=>get" + lockB);
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (lockB) {
                System.out.println(Thread.currentThread().getName() +
                        "lock:" + lockB + "=>get" + lockA);
            }
        }
    }

}
