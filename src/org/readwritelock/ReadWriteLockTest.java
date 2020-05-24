package org.readwritelock;

/**
 * 独占锁（写锁） 一次只能被一个线程占有
 * 共享锁（读锁） 多个线程可以同时占有
 * ReadWriteLock
 * 读-读 可以共存！
 * 读-写 不能共存！
 * 写-写 不能共存！
 */
public class ReadWriteLockTest {
    public static void main(String[] args) {
//        ReadWriteLockTest.mycatch();
        ReadWriteLockTest.mycatch();
    }

    public static void mycatch() {
        MyCache myCache = new MyCache();
        // 多线程写入
        for (int i = 1; i <= 10; i++) {
            final int temp = i;
            new Thread(() -> {
                myCache.put(temp + "", temp + "");
            }, String.valueOf(i)).start();
        }

        //多线程读取
        for (int i = 1; i <= 10; i++) {
            final int temp = i;
            new Thread(() -> {
                myCache.get(temp + "");
            }, String.valueOf(i)).start();
        }
    }

    public static void mycatchLock() {
        MyCacheLock myCacheLock = new MyCacheLock();

        //多线程写入
        for (int i = 1; i <= 10; i++) {
            final int temp = i;
            new Thread(() -> {
                myCacheLock.put(temp + "", temp + "");
            }, String.valueOf(i)).start();
        }

        //多线程读取
        for (int i = 1; i <= 10; i++) {
            final int temp = i;
            new Thread(() -> {
                myCacheLock.get(temp + "");
            }, String.valueOf(i)).start();
        }
    }
}
