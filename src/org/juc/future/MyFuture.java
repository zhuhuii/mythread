package org.juc.future;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 *
 */
public class MyFuture {
    public static void main(String[] args) {
        //runAsync();
        //supplyAsync();
        supplyAsyncAjax();
    }

    /**
     * CompletableFuture 实现 ajax
     */
    private static void supplyAsyncAjax() {
        CompletableFuture<Integer> completableFuture = CompletableFuture.supplyAsync(() -> {
            int num = 1 / 0;
            return 1024;
        });

        System.out.println("main thread ~~");
        try {
            System.out.println(completableFuture.whenComplete((t, u) -> {
                System.out.println("t => " + t);    // 正常返回的结果
                System.out.println("u => " + u);    // 错误信息：java.util.concurrent.CompletionException: java.lang.ArithmeticException: / by zero
            }).exceptionally((e) -> {
                e.printStackTrace();
                return 500;
            }).get());

            /**
             * succee Code 200
             * error Code 404 500
             */

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    /**
     * 有返回值的的 supplyAsync 异步回调
     */
    private static void supplyAsync() {
        CompletableFuture<Integer> completableFuture = CompletableFuture.supplyAsync(() -> {
            return 1024;
        });

        System.out.println("main thread ~~");
        try {
            System.out.println(completableFuture.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    /**
     * 没有返回值的 runAsync 异步回调
     */
    private static void runAsync() {
        CompletableFuture<Void> completableFuture = CompletableFuture.runAsync(() -> {
            System.out.println(Thread.currentThread().getName() + " runAsync=>Void");
        });

        System.out.println("main thread ~");
        try {
            // 得到结果
            completableFuture.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
