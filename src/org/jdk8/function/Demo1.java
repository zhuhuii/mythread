package org.jdk8.function;

import java.util.function.Function;

/**
 * 函数式接口： 只有一个方法的接口
 *
 * Function 函数型接口, 有一个输入参数，有一个输出
 * 只要是 函数型接口 可以 用 lambda表达式简化
 */
public class Demo1 {
    public static void main(String[] args) {
//        Function<String, String> function = new Function<String, String>() {
//            @Override
//            public String apply(String str) {
//                return str;
//            }
//        };

        Function<Integer, String> function = (id)->{return id.toString();};
        System.out.println(function.apply(1024));
    }
}
