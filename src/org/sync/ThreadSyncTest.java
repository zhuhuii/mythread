package org.sync;

import java.util.concurrent.TimeUnit;

/**
 * @author zhuhui
 * @date 2020/5/23 11:48
 */
public class ThreadSyncTest {
    public static void main(String[] args) throws InterruptedException {
        ThreadSync sync = new ThreadSync();
        Thread thread1 = new Thread(() -> {
            sync.increCountFun();
        });

        Thread thread2 = new Thread(() -> {
            sync.increCountObj();
        });

        Thread thread3 = new Thread(() -> {
            ThreadSync.increCountStatic();
        });

        Thread thread4 = new Thread(() -> {
            ThreadSync.showThreadName();
        });

        Thread thread5 = new Thread(() -> {
            sync.show();
        });

        /*
         * synchronized 锁方法和锁对象 == 对象锁
         * synchrnoized 锁静态方法 == 类锁
         * 对象锁和类锁互不干扰
         */
        thread1.setPriority(10);
        thread1.start();
        thread2.start();
        thread3.start();
        thread5.start();

        Thread.sleep(5 * 1000);
        System.out.println("\n线程释放(对象或类)锁才能继续执行下一个synchronized锁");
    }
}
