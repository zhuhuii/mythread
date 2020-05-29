package org.single;

/**
 * double-checked locking
 * 双检锁/双重校验锁
 *
 * <p>
 * 这种方式采用双锁机制，安全且在多线程情况下能保持高性能。
 */
public class DCL {

    // 防止指令重排
    private volatile static DCL dcl;

    private DCL() {
        System.out.println(Thread.currentThread().getName() + " -> DCL ok");
    }

    /**
     * 不在方法上加锁（防止速度变慢）
     */
    public static DCL getInstance() {
        if (dcl == null) {
            synchronized (DCL.class) {
                if (dcl == null) {
                    dcl = new DCL();    // 不是原子操作
                    /**
                     * 1、分配内存空间
                     * 2、执行构造方法，初始化对象
                     * 3、将对象指向这个内存空间
                     *
                     * 123 正常步骤
                     * 132 A    // 指令重排
                     * 1   B    // 此时 lazyMan 还没有完成构造，return null;
                     */
                }
            }
        }
        return dcl;
    }

}
