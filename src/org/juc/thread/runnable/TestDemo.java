package org.juc.thread.runnable;

/**
 * @author zhuhui
 * @date 2020/5/22 20:22
 */
public class TestDemo {
    public static void main(String[] args) {
        MyRunnable myRunnable1 = new MyRunnable();
        MyRunnable myRunnable2 = new MyRunnable();

        // 给线程取名
        Thread thread1 = new Thread(myRunnable1, "A");
        Thread thread2 = new Thread(myRunnable2,"我是线程B!");
        thread1.start();
        thread2.start();
    }
}
