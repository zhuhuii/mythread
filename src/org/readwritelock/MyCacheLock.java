package org.readwritelock;


import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class MyCacheLock {

    private volatile Map<String, Object> map = new HashMap<>();
    private ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock();

    /**
     * 读，取，所有人都可以读
     */
    public void get(String key) {
        reentrantReadWriteLock.readLock().lock();
        System.out.println(Thread.currentThread().getName() + "读取" + key);
        Object o = map.get(key);
        System.out.println(Thread.currentThread().getName() + "读取OK");
        reentrantReadWriteLock.readLock().unlock();
    }

    /**
     * 写，存入的时候，只希望同时只有一个线程写
     */
    public void put(String key, Object value) {
        reentrantReadWriteLock.writeLock().lock();
        System.out.println(Thread.currentThread().getName() + "写入" + value);
        map.put(key, value);
        System.out.println(Thread.currentThread().getName() + "写入OK");
        reentrantReadWriteLock.writeLock().unlock();

    }
}
