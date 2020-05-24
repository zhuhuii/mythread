package org.thread.thread;

/**
 * @author zhuhui
 * @date 2020/5/22 20:22
 */
public class TestDemo {
    public static void main(String[] args) {
        MyThread myThread = new MyThread("A");
        MyThread myThread2 = new MyThread("我是线程B=====");
        myThread.start();
        myThread2.start();
    }
}
