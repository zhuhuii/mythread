package org.juc.forkjoin;

import java.util.concurrent.RecursiveTask;

/**
 * Forkjoin：将一个大任务分成无数个小任务，合并所有小任务结果返回！（分治思想）
 *
 *  1、forkjoinPool 通过它来执行
 *  2、计算任务 forkjoinPool.execute(ForkJoinTask task)
 *  3. 计算类要继承 ForkJoinTask
 */
public class MyForkJoin extends RecursiveTask<Long> {
    private Long start;
    private Long end;

    // 临界值
    private Long temp = 1_0000L;

    public MyForkJoin(Long start, Long end) {
        this.start = start;
        this.end = end;
        this.temp = temp;
    }

    @Override
    protected Long compute() {
        if (end - start < temp) {
            long sum = 0L;
            for (long i = start; i <= end; i++) {
                sum += i;
            }
            return sum;
        } else {
            // forkjoin 递归
            Long middle = (end + start) / 2;    // 中间值
            MyForkJoin task1 = new MyForkJoin(start, middle);
            MyForkJoin task2 = new MyForkJoin(middle + 1, end);
            task1.fork();   // 拆分任务，把任务压入线程队列
            task2.fork();   // 拆分任务，把任务压入线程队列
            return task1.join() + task2.join();
        }
    }
}
