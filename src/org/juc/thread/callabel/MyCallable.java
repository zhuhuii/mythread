package org.juc.thread.callabel;

import java.util.concurrent.Callable;

/**
 * @author zhuhui
 * @date 2020/5/24 18:40
 */
public class MyCallable implements Callable<String> {
    @Override
    public String call() throws Exception {
        System.out.println("我执行了：hello world");
        return "OK~~~~~~~";
    }
}
