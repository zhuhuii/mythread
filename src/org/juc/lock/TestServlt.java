package org.juc.lock;

/**
 * @author zhuhui
 * @date 2020/5/23 10:52
 */
public class TestServlt {
    public static void main(String[] args) throws InterruptedException {
        SimpleServlet simpleServlet = new SimpleServlet();
        new Thread(()->{simpleServlet.sale();}).start();
        new Thread(()->{simpleServlet.sale();}).start();
        new Thread(()->{simpleServlet.sale();}).start();
        new Thread(()->{simpleServlet.sale();}).start();

        Thread.sleep(2* 1000);
        System.out.println(simpleServlet.getCount());
    }
}
