package org.javalock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 可重入锁（Reetrant Lock）
 */
public class Lock2 {
    // 可重入锁
    public static void main(String[] args) {
        Lock2 rlock = new Lock2();

        rlock.phoneSync();

        rlock.phoneLock();
    }

    /**
     * synchronized 版
     */
    public void phoneSync(){
        new Thread(()->{
            phone.sms();
        },"A").start();

        new Thread(()->{
            phone.sms();
        },"B").start();
    }

    /**
     * lock 版
     */
    public void phoneLock(){
        new Thread(()->{
            xiaomi.sms();
        },"XXX").start();

        new Thread(()->{
            xiaomi.sms();
        },"YYY").start();
    }
}

class phone{
    public synchronized static void sms(){
        System.out.println(Thread.currentThread().getName() + " => 发短信");
        call();
    }

    public synchronized static void call(){
        System.out.println(Thread.currentThread().getName() + " => 打电话");
    }
}

class xiaomi{

    private static Lock lock = new ReentrantLock();

    public static void sms(){
        try {
            // 细节问题：lock 锁必须配对，否则就会死在里面
            lock.lock();
            lock.lock();
            System.out.println(Thread.currentThread().getName() + " => 发短信");
            call(); // 这里也有锁，一定要unlock释放锁
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
            lock.unlock();
        }
    }

    public  static void call(){
        try {
            lock.lock();
            System.out.println(Thread.currentThread().getName() + " => 打电话");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}

