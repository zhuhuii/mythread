package org.javalock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/**
 * 自定义一个不可重入的自旋锁
 */
public class Lock3 {
    public static void main(String[] args) throws InterruptedException {
        new Thread(()->{
            try {
                MySpinLock.myLock();
                TimeUnit.SECONDS.sleep(3);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                MySpinLock.myUnLock();
            }
        },"A").start();


        TimeUnit.SECONDS.sleep(1);


        new Thread(()->{
            try {
                MySpinLock.myLock();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                MySpinLock.myUnLock();
            }
        },"B").start();
    }
}

/**
 * 自旋锁
 */
class MySpinLock {
    private static AtomicReference<Thread> atomicReference = new AtomicReference();

    public static void myLock() {
        Thread thread = Thread.currentThread();
        while (!atomicReference.compareAndSet(null, thread)) {
            //System.out.println("自旋ing");
        }
        System.out.println(thread.getName() + " ==> myLock");
    }

    public static boolean myUnLock() {
        Thread thread = Thread.currentThread();
        System.out.println(thread.getName() + " ==> myUnLock");
        return atomicReference.compareAndSet(thread, null);
    }
}
