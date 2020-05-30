package org.juc.single;

/**
 * 懒汉模式
 *      1.将该类的构造方法定义为私有方法
 *      2.在该类内提供一个静态方法，调用这个方法时，如果类持有的引用不为空就返回这个引用，如果类保持的引用为空就创建该类的实例并将实例的引用赋予该类保持的引用。
 */
public class LazyMan {
    private static LazyMan lazyMan;

    public static LazyMan getInstance() {
        if (lazyMan == null) {
            lazyMan = new LazyMan();
        }
        return lazyMan;
    }

    private LazyMan() {
        System.out.println(Thread.currentThread().getName() + " -> LazyMan ok");
    }
}
