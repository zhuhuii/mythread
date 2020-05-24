package org.blockingqueue;

import com.sun.org.apache.bcel.internal.generic.LoadClass;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 同步队列
 * 和其他的BlockingQueue 不一样， SynchronousQueue 不存储元素
 * put了一个元素，必须从里面先take取出来，否则不能在put进去值！
 */

public class SynchronizedQueue {
    public static void main(String[] args) {
        BlockingQueue blockingQueue = new SynchronousQueue();

        new Thread(()->{
            try {
                System.out.println(Thread.currentThread().getName()+" put 1");
                blockingQueue.put("1");
                System.out.println(Thread.currentThread().getName()+" put 2");
                blockingQueue.put("2");
                System.out.println(Thread.currentThread().getName()+" put 3");
                blockingQueue.put("3");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"线程A").start();

       new Thread(()->{
           try {
               TimeUnit.SECONDS.sleep(3);
               System.out.println(Thread.currentThread().getName()+"=>"+blockingQueue.take());
               TimeUnit.SECONDS.sleep(3);
               System.out.println(Thread.currentThread().getName()+"=>"+blockingQueue.take());
               TimeUnit.SECONDS.sleep(3);
               System.out.println(Thread.currentThread().getName()+"=>"+blockingQueue.take());
           } catch (InterruptedException e) {
               e.printStackTrace();
           }
       },"BBB").start();
    }
}
