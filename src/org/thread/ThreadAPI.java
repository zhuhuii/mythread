package org.thread;

import org.thread.runnable.MyRunnable;

/**
 * @author zhuhui
 * @date 2020/5/22 21:34
 */
public class ThreadAPI {
    public static void main(String[] args) {
        ThreadAPI threadDemo = new ThreadAPI();

//        threadDemo.threadAPI();
//        threadDemo.sleep();
//        threadDemo.join();
        threadDemo.yield();
    }

    /**
     * Thread线程类API
     * 1.设置线程名
     * 2.守护线程
     * 3.优先级线程
     * 4.线程生命周期（重点）
     */
    public void threadAPI() {
        // 输出当前线程名
        System.err.println(Thread.currentThread().getName());

        MyRunnable myRunnable1 = new MyRunnable();
        MyRunnable myRunnable2 = new MyRunnable();
        // 给线程取名
        Thread thread1 = new Thread(myRunnable1, "A");
        Thread thread2 = new Thread(myRunnable2, "我是线程B!");

        // 设置线程优先级:Java提供的优先级默认是5，最低是1，最高是10
        thread1.setPriority(10);
        thread2.setPriority(1);
        // 设置守护线：线程1和主线程执行完了，我们的守护线程就不执行了~
        thread2.setDaemon(true);
        thread1.start();
        thread2.start();
    }

    /**
     * 线程的生命周期
     * 1.sleep
     * 2.yield
     * 3.join
     * 4.interrupt
     */
    public void sleep() {
        for (int i = 0; i < 10; i++) {
            if (i == 2) {
                try {
                    Thread.sleep(1 * 1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(i);
        }
    }

    private int count = 0;
    public void join() {
        Runnable myRunnable = () -> {
            for (int i = 0; i < 100; i++) {
                count++;
            }
        };

        Thread thread = new Thread(myRunnable);
        thread.start(); // main线程等待thread执行完毕
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            System.out.println(count);
        }
    }

    public void yield() {
        Runnable run1 = () -> {
            String name = Thread.currentThread().getName();
            for (int i = 0; i < 20; i++) {
                System.out.println(name + "---" + i);
                if (i == 5) {
                    Thread.yield();
                }
            }
        };
        Runnable run2 = () -> {
            String name = Thread.currentThread().getName();
            for (int i = 0; i < 20; i++) {
                System.out.println(name + "---" + i);
            }
        };

        Thread thread1 = new Thread(run1, "A");
        Thread thread2 = new Thread(run2, "线程BBBBB");
        thread1.start();
        thread2.start();
    }
}
