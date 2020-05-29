package org.single;

/**
 * 单例模式：https://www.runoob.com/design-pattern/singleton-pattern.html
 */
public class SingleTest {

    public static void main(String[] args) {
        for (int i = 1; i <= 100000; i++) {
            new Thread(() -> {
                /**
                 * 多线程下会创建多个对象，线程不安全
                 */
                // LazyMan.getInstance();

                /**
                 * 线程安全的单例模式
                 */
                // Hury.getInstance();
                // DCL.getInstance();
                Holder.getInstance();
            }).start();
        }
    }
}
