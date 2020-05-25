package org.jdk8.function;

import java.util.function.Supplier;

/**
 * Supplier 供给型接口
 */
public class Demo4 {
    public static void main(String[] args) {
//        Supplier<Integer> supplier = new Supplier<Integer>() {
//            @Override
//            public Integer get() {
//                return 10086;
//            }
//        };

        Supplier<Integer> supplier = ()->{
            return 10086;
        };

        System.out.println(supplier.get());
    }
}
