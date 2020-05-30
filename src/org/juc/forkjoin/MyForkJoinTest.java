package org.juc.forkjoin;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.stream.LongStream;

/**
 * 同一个任务，别人效率高你几十倍！
 */
public class MyForkJoinTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        long start = 1L;
        long end = 40_0000_0000L;   // 40亿

        sum(start, end);
        forkJoin(start, end);
        parallel(start, end);   // 并行流最快
    }

    /**
     * 传统的计算方式
     *
     * @return
     */
    private static void sum(Long start, long end) {
        long l = System.currentTimeMillis();

        long sum = 0L;
        for (long i = start; i <= end; i++) {
            sum += i;
        }

        long e = System.currentTimeMillis();
        System.out.println("for循环：" + sum);
        System.out.println("for循环：" + (e - l));
    }

    /**
     * Forkjoin
     * @param start
     * @param end
     */
    private static void forkJoin(Long start, long end) {
        long l = System.currentTimeMillis();

        ForkJoinPool forkJoinPool = new ForkJoinPool();
        ForkJoinTask<Long> task = new MyForkJoin(start, end);
        ForkJoinTask<Long> submit = forkJoinPool.submit(task);
        Long sum = null;
        try {
            sum = submit.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        long e = System.currentTimeMillis();
        System.out.println("forkjoin：" + sum);
        System.out.println("forkjoin：" + (e - l));
    }

    /**
     * 并行流
     * @param start
     * @param end
     */
    private static void parallel(long start, long end) {
        long l = System.currentTimeMillis();
        long sum = LongStream.rangeClosed(start, end).parallel().reduce(0, (x, y) -> x + y);
        long e = System.currentTimeMillis();
        System.out.println("parallel并行流：" + sum);
        System.out.println("parallel并行流：" + (e - l));
    }
}
