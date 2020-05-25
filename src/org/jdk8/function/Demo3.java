package org.jdk8.function;

import java.util.function.Consumer;
import java.util.function.Predicate;

/**
 * Consumer 消费型接口
 */
public class Demo3 {
    public static void main(String[] args) {
//        Consumer<String> consumer = new Consumer<String>() {
//            @Override
//            public void accept(String s) {
//                System.out.println(s);
//            }
//        };

        Consumer<String> consumer = (str)->{
            System.out.println(str);
        };

        consumer.accept("hello~~");
    }
}
