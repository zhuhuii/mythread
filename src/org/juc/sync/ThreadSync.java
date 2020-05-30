package org.juc.sync;

import java.util.concurrent.TimeUnit;

/**
 * @author zhuhui
 * @date 2020/5/23 10:52
 */
public class ThreadSync {

    /**
     * synchronized 锁方法
     */
    public synchronized void increCountFun() {
        for (int i = 0; i < 3; i++) {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("function running...");
        }
    }

    /**
     * synchronized 对象锁
     */
    public void increCountObj() {
        synchronized (this) {
            for (int i = 0; i < 3; i++) {
                System.out.println("synchronized object running...");
            }
        }
    }

    /**
     * synchronized 锁静态方法
     */
    public synchronized static void increCountStatic() {
        for (int i = 0; i < 3; i++) {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Static function running...      静态方法锁住了");
        }
    }

    /**
     * synchronized 锁静态方法2
     */
    public synchronized static void showThreadName() {
        for (int i = 0; i < 3; i++) {
            System.out.println(Thread.currentThread().getName());
        }
    }


    /**
     * 测试类或对象被锁了，类中成员/类方法是否可以访问
     * 结果：可以访问，但是线程不安全！
     */
    public static void show() {
        for (int i = 0; i < 3; i++) {
            try {
                TimeUnit.SECONDS.sleep((long) 0.5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("我是普通方法-----------------》线程不安全");
        }
    }
}
