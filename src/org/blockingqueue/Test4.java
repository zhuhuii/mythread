package org.blockingqueue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * 阻塞队列，四组API，可以混用
 * 1.抛出异常
 * 2.有返回值，不抛出异常
 * 3.阻塞等待
 * 4.超时等待
 */
public class Test4 {
    public static void main(String[] args) throws InterruptedException {
        BlockingQueue blockingQueue = new ArrayBlockingQueue(3);

        System.out.println(blockingQueue.offer("1",1, TimeUnit.SECONDS));
        System.out.println(blockingQueue.offer("1",1, TimeUnit.SECONDS));
        System.out.println(blockingQueue.offer("1",1, TimeUnit.SECONDS));
        System.out.println(blockingQueue.offer("2",1, TimeUnit.SECONDS));

        System.out.println("------------------------");
        System.out.println(blockingQueue.poll(1, TimeUnit.SECONDS));
        System.out.println(blockingQueue.poll(1, TimeUnit.SECONDS));
        System.out.println(blockingQueue.poll(1, TimeUnit.SECONDS));
        System.out.println(blockingQueue.poll(1, TimeUnit.SECONDS));
    }
}
