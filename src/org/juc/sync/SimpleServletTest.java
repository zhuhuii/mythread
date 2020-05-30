package org.juc.sync;

/**
 * @author zhuhui
 * @date 2020/5/23 10:52
 */
public class SimpleServletTest {
    public static void main(String[] args) {
        SimpleServlet simpleServlet = new SimpleServlet();
        Thread thread1 = new Thread(()->{simpleServlet.sale();});
        Thread thread2 = new Thread(()->{simpleServlet.sale();});

        thread1.start();
        thread2.start();
        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(simpleServlet.getCount());
    }
}
