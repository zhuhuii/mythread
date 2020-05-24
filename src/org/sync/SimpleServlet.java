package org.sync;

/**
 * @author zhuhui
 * @date 2020/5/23 10:52
 */
public class SimpleServlet {
    private int count;

    public int getCount() {
        return count;
    }

    public void sale() {
        for (int i = 0; i < 25000; i++) {
            count++;
        }
    }
}
