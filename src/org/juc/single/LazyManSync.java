package org.juc.single;

/**
 * 线程安全的饿汉模式（不推荐）
 *      优点：第一次调用才初始化，避免内存浪费。
 *      缺点：必须加锁 synchronized 才能保证单例，但加锁会影响效率。
 */
public class LazyManSync {

    private static LazyManSync lazyMan = new LazyManSync();

    /**
     * 线程安全，效率低
     */
    public synchronized static LazyManSync getInstance(){
       return lazyMan;
    }

    private LazyManSync(){};
}
