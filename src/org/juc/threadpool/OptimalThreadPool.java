package org.juc.threadpool;

import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 最大线程到底该如何定义
 * 1、CPU 密集型，几核，就是几，可以保持CPu的效率最高！
 * 2、IO 密集型 > 判断你程序中十分耗IO的线程（程序 15个大型任务 io十分占用资源！ maxPoolSize 可以设置成30）
 */
public class OptimalThreadPool {
    public static void main(String[] args) {
        // 获取CPU的核数
        System.out.println(Runtime.getRuntime().availableProcessors());

        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
                2,
                Runtime.getRuntime().availableProcessors(),
                3,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(50),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.DiscardOldestPolicy()
        );

        try {
            for (int i = 1; i <= 100 ; i++) {
                threadPoolExecutor.execute(()->{
                    System.out.println(Thread.currentThread().getName() + " OK");
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            threadPoolExecutor.shutdown();
        }
    }
}
