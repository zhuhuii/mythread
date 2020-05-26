package org.forkjoin;

import java.util.concurrent.RecursiveTask;

public class MyForkJoin extends RecursiveTask<Long> {
    private Long start;
    private Long end;
    private Long temp = 10000L;

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
            Long middle = (end+start)/2;
            MyForkJoin task1 = new MyForkJoin(start, middle);
            task1.fork();
            MyForkJoin task2 = new MyForkJoin(middle+1, end);
            task2.fork();
            return task1.join() + task2.join();
        }
    }
}
