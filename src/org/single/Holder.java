package org.single;

/**
 * 静态内部类，实现单例模式
 */
public class Holder {
    private Holder() {
        System.out.println(Thread.currentThread().getName() + " -> 静态内部类 ok");
    }

    // 线程安全，延迟加载
    public static Holder getInstance(){
        return innerHolder.HOLDER;
    }

    private static class innerHolder{
        private final static Holder HOLDER = new Holder();
    }

}
