package org.juc.producer;

/**
 * 唤醒后继续从 wait()处往下执行。
 *  如果是if，它不作判断就直接执行，造成错误。
 *  如果是while，会再次进入到条件判断，保证了数据的安全
 */
public class SyncDataWrong {
    //判断等待，业务，通知
    private int num = 0;

    /**
     * +1操作
     */
    public synchronized void increment() {
        if (num != 0) {
            try {
                // 等待
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        num++;
        if (num>1)
            System.out.println(Thread.currentThread().getName() + "==>" + num);
        //通知其他线程，我 +1 操作完成了
        this.notifyAll();
    }

    /**
     * -1操作
     */
    public synchronized void decrement() {
        if (num != 1) {
            try {
                // 等待
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        num--;
        if (num>1)
            System.out.println(Thread.currentThread().getName() + "==>" + num);
        //通知
        this.notifyAll();
    }
}
