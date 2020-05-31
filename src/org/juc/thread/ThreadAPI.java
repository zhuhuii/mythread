package org.juc.thread;

import org.juc.thread.runnable.MyRunnable;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * ThreadAPI （重要）
 */
public class ThreadAPI {
    public static void main(String[] args) throws InterruptedException {
        ThreadAPI threadDemo = new ThreadAPI();

//        threadDemo.threadAPI();
//        threadDemo.sleep();
//        threadDemo.join();
//        threadDemo.yield();
//        threadDemo.interrupt();
        threadDemo.Isinterrupd();
    }

    /**
     * Thread线程类API
     * 1.设置线程名
     * 2.守护线程
     * 3.优先级线程
     * 4.线程生命周期（重点）
     *  4.1、sleep
     *  4.2、join
     *  4.3、yield
     *  4.4、interrupt
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
        // 设置了优先级别之后，级别高 并不是说你一定被优先调度，而是你的被优先调度的概率高而已。
        thread1.setPriority(10);
        thread2.setPriority(1);

        // 设置守护线：线程1和主线程执行完了，我们的守护线程就不执行了~
        thread2.setDaemon(true);
        thread1.start();
        thread2.start();

        // 判断线程是否在执行
        System.out.println("thread1 => " + thread1.isAlive());
        System.out.println("thread2 => " + thread2.isAlive());
    }

    /**
     * 线程的生命周期
     * 1.sleep
     * 2.yield
     * 3.join
     * 4.interrupt
     */

    /**
     * sleep 线程进行暂时的休眠，计时等待
     */
    public void sleep() {
        // sleep 线程进行暂时的休眠，计时等待
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

    /**
     * 线程强制运行：join()
     * 线程强制运行期间，其他线程无法运行，必须等待此线程完成之后，才可以继续运行。
     */
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

    /**
     * 线程的礼让 yield
     *  一定是当前线程调用此方法，当前线程放弃获取的cpu时间片，由运行状态变会可运行状态，让OS再次选择线程。
     *  作用：让相同优先级的线程轮流执行，但并不保证一定会轮流执行。
     *  实际中无法保证yield()达到让步目的，因为让步的线程还有可能被线程调度程序再次选中。
     *  Thread.yield()不会导致阻塞。
     */
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

    /**
     * interrupt不会真正停⽌⼀个线程
     * 它仅仅是给这个线程发了⼀个信号告诉它，它应该要结束了
     */
    public void interrupt() {
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
        System.out.println("线程启动了，线程是否存活：" + thread.isAlive() + "\n");

        try {
            Thread.currentThread().sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("睡眠1秒后，线程中断？：" + thread.isInterrupted());
        System.out.println("设置中断标志👇↓ ~ \n");

        // 设置了⼀个中断标志
        thread.interrupt();
        System.out.println("线程中断？：" + thread.isInterrupted());
        System.out.println("线程是否存活？：" + thread.isAlive());
        System.out.println("num = " + num);
    }

    /**
     * sleep方法内部会不断的检查中断状态的值，从而自己抛出InterruptedException.
     */
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

    private int count = 0;

}
