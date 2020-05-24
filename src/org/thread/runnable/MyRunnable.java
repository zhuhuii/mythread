package org.thread.runnable;

/**
 * @author zhuhui
 * @date 2020/5/22 20:24
 */
public class MyRunnable implements Runnable {

    @Override
    public void run() {
        // 当前线程名
        String threadName = Thread.currentThread().getName();
        System.err.println(threadName);

        for (int i = 1; i <= 50; i++) {
            try {
                long millis = (long) (Math.random() * (30));
                Thread.sleep(millis);
                System.out.println(threadName + "：" + i);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
