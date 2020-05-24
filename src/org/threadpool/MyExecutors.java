package org.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Executors 工具类、3大方法
 * 本质是 ThreadPoolExecutor
 */
public class MyExecutors {
    public static void main(String[] args) {
        // 单个线程
        //ExecutorService threadPool = Executors.newSingleThreadExecutor();

        // 创建一个固定大小的线程池
        //ExecutorService threadPool = Executors.newFixedThreadPool(5);

        // 可伸缩的，遇强则强，遇弱则弱
        ExecutorService threadPool = Executors.newCachedThreadPool();

        try {
            for (int i = 1; i <= 100; i++) {
                // 使用了线程池之后，使用线程池来创建线程
                threadPool.execute(() -> {
                    System.out.println(Thread.currentThread().getName());
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            threadPool.shutdown();
        }
    }
}
