package org.forkjoin;

import java.util.concurrent.ExecutionException;

/**
 * @author zhuhui
 * @date 2020/5/26 17:08
 */
public class MyForkJoinTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        long l = System.currentTimeMillis();

        long sum = 1L;
        for (long i = 1L; i <= 20L; i++) {
            sum=sum*i;
        }

//        ForkJoinPool forkJoinPool = new ForkJoinPool();
//        ForkJoinTask<Long> task = new MyForkJoin(1L, 10_0000_000L);
//        ForkJoinTask<Long> submit = forkJoinPool.submit(task);
//        Long sum = submit.get();

        long e = System.currentTimeMillis();
        System.out.println(sum);
        System.out.println(e - l);
    }
}
