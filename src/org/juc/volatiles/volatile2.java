package org.juc.volatiles;

/**
 * 1.保证可见性
 * 2.不保证原子性
 * 3.禁止指令重排
 */
public class volatile2 {
    // volatile 不保证原子性
    private volatile static int num = 0;

    public static void main(String[] args) {
        //理论上num结果应该为 50 万
        for (int i = 1; i <= 50; i++) {
            new Thread(() -> {
                for (int j = 1; j <= 10000; j++) {
                    num++;
                }
            }).start();
        }

        while (Thread.activeCount() > 2) {  // main线程， gc线程
            Thread.yield();
        }

        System.out.println(Thread.currentThread().getName() + " " + num);
    }
}
