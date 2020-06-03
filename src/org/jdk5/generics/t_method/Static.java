package org.jdk5.generics.t_method;

import org.jdk8.stream.User;

/**
 * 1、静态泛型方法
 * 2、泛型方法与可变参数
 */
class Static {
    /**
     * 如果在类中定义使用泛型的静态方法，需要添加额外的泛型声明（将这个方法定义成泛型方法）
     * 即使静态方法要使用泛型类中已经声明过的泛型也不可以。
     * 如：public static void show(T t){..},此时编译器会提示错误信息：
     " StaticGenerator cannot be refrenced from static context"
     */
    public static <T> void show(T t){
        System.out.println("static -> 静态方泛型方法：" + t.toString());
    }

    /**
     * 泛型方法与可变参数
     */
    public static <T> void printMsg(T... args) {
        for (T t : args) {
            System.out.println("item => " + t.toString());
        }
    }

    public static void main(String[] args) {
        Static.show("中国");
        Static.printMsg(123, "zhangsan", true, 66.99, 10086L, new User(1, "lisi", 18));

        /**
         * java.lang.NullPointerException 》 方法中的可变参数需要判断是否为空
         * // Static.printMsg();
         * // Static.printMsg(null);
         */
    }
}
