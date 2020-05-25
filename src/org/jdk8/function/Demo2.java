package org.jdk8.function;

import java.util.function.Function;
import java.util.function.Predicate;

/**
 * 断定型接口：有一个输入参数，返回值只能是 布尔值！
 */
public class Demo2 {
    public static void main(String[] args) {
//        Predicate<String> predicate = new Predicate<String>() {
//            @Override
//            public boolean test(String s) {
//                if (s.isEmpty())
//                    return true;
//                return false;
//            }
//        };


        Predicate<String> predicate = (str) -> {
            if (str.isEmpty())
                return true;
            return false;
        };

        System.out.println(predicate.test(""));
        System.out.println(predicate.test("abc"));

    }
}
