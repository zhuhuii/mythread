package org.threadpool;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * ThreadPoolExecutor 七大参数（重要）
 * <p>
 * RejectedExecutionHandler：拒绝策略
 * new ThreadPoolExecutor.AbortPolicy()            // 银行满了，还有人进来，不处理这个人的，抛出异常
 * new ThreadPoolExecutor.CallerRunsPolicy()       // 哪来的去哪里！
 * new ThreadPoolExecutor.DiscardPolicy()           //队列满了，丢掉任务，不会抛出异常！
 * new ThreadPoolExecutor.DiscardOldestPolicy()     //队列满了，尝试去和最早的竞争，也不会抛出异常！
 */
public class MyThreadPoolExecutor {
    public static void main(String[] args) {
        // 自定义线程池！工作 ThreadPoolExecutor
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
                5,  // 核心线程池大小
                10,  // 最大核心线程池大小
                3,  // 超时了没有人调用就会释
                TimeUnit.SECONDS,   // 超时单位
                new ArrayBlockingQueue<>(10),   // 阻塞队列
                Executors.defaultThreadFactory(),   // 线程工厂：创建线程的，一般不用动
                new ThreadPoolExecutor.AbortPolicy()    // 拒绝策略
        );

        try {
            // 最大承载：Deque + max
            // 超过 RejectedExecutionException
            for (int i = 1; i <= 100; i++) {
                // 使用了线程池之后，使用线程池来创建线程
                threadPoolExecutor.execute(() -> {
                    System.out.println(Thread.currentThread().getName() + " ok");
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 线程池用完，程序结束，关闭线程池
            threadPoolExecutor.shutdown();
        }

    }
}
