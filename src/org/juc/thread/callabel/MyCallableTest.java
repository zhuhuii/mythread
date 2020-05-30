package org.juc.thread.callabel;


import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class MyCallableTest {
    public static void main(String[] args) {
        MyCallable myCallable = new MyCallable();
        FutureTask<String> futureTask = new FutureTask<>(myCallable);
        Thread thread = new Thread(futureTask);
        thread.start();

        try {
            System.out.println(futureTask.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
