package org.juc.blockingqueue;


import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * 阻塞队列，四组API，可以混用
 * 1.抛出异常
 * 2.有返回值，不抛出异常
 * 3.阻塞等待
 * 4.超时等待
 */
public class Test1 {
    public static void main(String[] args) {
        // 一定要初始化长度
        BlockingQueue blockingQueue = new ArrayBlockingQueue(3);

        System.out.println(blockingQueue.add("1"));;
        System.out.println(blockingQueue.add("2"));;
        System.out.println(blockingQueue.add("3"));;
        // Exception in thread "main" java.lang.IllegalStateException: Queue full
        // blockingQueue.add("4");

        System.out.println("------------------------");
        System.out.println(blockingQueue.remove());
        System.out.println(blockingQueue.remove());
        System.out.println(blockingQueue.remove());
        // Exception in thread "main" java.util.NoSuchElementException
        System.out.println(blockingQueue.remove());
    }
}
