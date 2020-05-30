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
public class Test2 {
    public static void main(String[] args) {
        BlockingQueue blockingQueue = new ArrayBlockingQueue(3);

        System.out.println(blockingQueue.offer("1"));
        System.out.println(blockingQueue.offer("2"));
        System.out.println(blockingQueue.offer("3"));
        System.out.println(blockingQueue.offer("4"));  // Queue full => false

        System.out.println("------------------------");
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());   // NoSuchElementException => null
    }
}
