package org.single;

/**
 * 饿汉模式
 *      1.构造方法私有化
 *      2.提供一个静态方法获取实例
 */
public class Hury {

    byte[] bytes1 = new byte[1024];
    byte[] bytes2 = new byte[1024];
    byte[] bytes3 = new byte[1024];
    byte[] bytes4 = new byte[1024];
    byte[] bytes5 = new byte[1024];
    byte[] bytes6 = new byte[1024];

    // 可能浪费空间
    private static Hury hury = new Hury();

    public static Hury getInstance(){
       return hury;
    }

    private Hury(){
        System.out.println(Thread.currentThread().getName() + " -> Hury ok");
    };
}
