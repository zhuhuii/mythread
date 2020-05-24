package org.producer;

/**
 * 生产者消费者模式
 */
public class SyncData {
    //while判断等待，业务，通知
    private int num = 0;

    /**
     * +1操作
     */
    public synchronized void increment() {
        // while可以避免虚假唤醒
        while (num != 0) {
            try {
                // 等待
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        num++;
        System.out.println(Thread.currentThread().getName() + "==>" + num);
        //通知其他线程，我 +1 操作完成了
        this.notifyAll();
    }

    /**
     * -1操作
     */
    public synchronized void decrement() {
        while (num != 1) {
            try {
                // 等待
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        num--;
        System.out.println(Thread.currentThread().getName() + "==>" + num);
        //通知
        this.notifyAll();
    }
}
