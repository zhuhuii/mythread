package org.volatiles;

import java.util.concurrent.TimeUnit;

/**
 * 1.保证可见性
 * 2.不保证原子性
 * 3.禁止指令重排
 */
public class volatile1 {
    // 不加 volatile 程序就会死循环！
    // 加 volatile 可以保证可见性
    private static int num = 0;

    public static void main(String[] args) {
        new Thread(() -> {
            while (num == 0) {  // 线程 1 对主内存的变化不知道的

            }
        }).start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        num = 1;
        System.out.println("num = " + num);
    }
}
