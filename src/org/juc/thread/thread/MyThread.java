package org.juc.thread.thread;

public class MyThread extends Thread {
    private String name;

    public MyThread(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        for (int i = 1; i <= 100; i++) {
            // 公式：(int)(Math.random()*(n-m)+m)，生成大于等于m小于n的随机数；
            int num = (int) (Math.random() * (3 - 1) + 1);
            System.out.println(name + "打印：" + i % num);
        }
    }
}
