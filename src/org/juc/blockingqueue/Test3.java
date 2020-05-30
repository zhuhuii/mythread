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
public class Test3 {
    public static void main(String[] args) throws InterruptedException {
        BlockingQueue blockingQueue = new ArrayBlockingQueue(3);

        blockingQueue.put("1");
        blockingQueue.put("2");
        blockingQueue.put("3");
        //blockingQueue.put("4"); // 队列已满，一直阻塞等待

        System.out.println("------------------------");
        System.out.println(blockingQueue.take());
        System.out.println(blockingQueue.take());
        System.out.println(blockingQueue.take());
        System.out.println(blockingQueue.take());   // 没有这个元素，一直阻塞
    }
}
