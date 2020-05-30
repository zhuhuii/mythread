package org.juc.thread;

import org.juc.thread.runnable.MyRunnable;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author zhuhui
 * @date 2020/5/22 21:34
 */
public class ThreadAPI {
    public static void main(String[] args) throws InterruptedException {
        ThreadAPI threadDemo = new ThreadAPI();

//        threadDemo.threadAPI();
//        threadDemo.sleep();
//        threadDemo.join();
//        threadDemo.yield();
//        threadDemo.interrupd();
        threadDemo.Isinterrupd();
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
        } finally {
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

    public void interrupd() {
        AtomicInteger num = new AtomicInteger();
        Thread thread = new Thread(() -> {
            // 若未发⽣中断，就正常执⾏任务
            while (!Thread.currentThread().isInterrupted()) {
                // 正常任务代码……
                num.getAndIncrement();
            }
            // 中断的处理代码……    doSomething();
            System.out.println("线程理论上中断了");
        });
        thread.start();
        boolean alive = thread.isAlive();
        System.out.println("线程是否存活：" + alive + "\n");

        try {
            Thread.currentThread().sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("线程中断？：" + thread.isInterrupted());

        // 设置了⼀个中断标志
        thread.interrupt();
        System.out.println("线程中断？：" + thread.isInterrupted());
        System.out.println("线程是否存活？：" + thread.isAlive());
        System.out.println("num = " + num);
    }

    public void Isinterrupd() {
        Thread thread = new Thread(() -> {
            int num = 0;
            while (num < 1000) {
                try {
                    // 睡个半秒钟我们再执⾏
                    Thread.sleep(500);
                    System.out.println(num++);

                } catch (InterruptedException e) {

                    // 判断该阻塞线程是否还在
                    System.out.println(Thread.currentThread().isAlive());
                    // 判断该线程的中断标志位状态
                    System.out.println(Thread.currentThread().isInterrupted());

                    System.out.println("In Runnable");
                    e.printStackTrace();
                }
            }
        });

        // 创建线程并启动
        System.out.println("This is main ");
        thread.start();
        try {
            // 在 main线程睡个3秒钟
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // 设置中断
        thread.interrupt();
    }
}
