package org.juc.producer;

/**
 * @author zhuhui
 * @date 2020/5/23 17:32
 */
public class AppProducer {

    /**
     * 生产者消费者的核心：
     * 1、等待（while判断等待）
     * 2、执行业务
     * 3、通知（唤醒线程）
     */
    public static void main(String[] args) {
//        syncData();
//        lockData();
//        conditionSignal();

        syncDataWrong();
    }

    /**
     * synchronized 实现
     */
    private static void syncData() {
        SyncData data = new SyncData();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                data.increment();
            }
        }, "A").start();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                data.decrement();
            }
        }, "B").start();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                data.increment();
            }
        }, "C").start();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                data.decrement();
            }
        }, "D").start();
    }

    /**
     * lock 实现
     */
    private static void lockData() {
        LockData data = new LockData();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                data.increment();
            }
        }, "A").start();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                data.decrement();
            }
        }, "B").start();
    }

    /**
     * lock 实现精准唤醒
     */
    private static void conditionSignal() {
        LockDataCondition lockCondition = new LockDataCondition();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    lockCondition.printA();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    lockCondition.printB();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    lockCondition.printC();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    /**
     * if判断等待条件会产生的错误： 虚假唤醒
     */
    private static void syncDataWrong() {
        SyncDataWrong data = new SyncDataWrong();
        new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                data.increment();
            }
        }, "A").start();
        new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                data.decrement();
            }
        }, "B").start();
        new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                data.increment();
            }
        }, "C").start();
        new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                data.decrement();
            }
        }, "D").start();
    }
}
