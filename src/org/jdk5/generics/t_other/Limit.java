package org.jdk5.generics.t_other;

import java.util.List;

/**
 * 泛型上下边界
 */
class Limit {
    /**
     * 类型实参只准传入 Number 类型的子类
     */
    public static void showKeyValue1(Generic<? extends Number> obj) {
        System.out.println("key == " + obj.getKey());
    }

    /**
     * 类型实参只准传入 Integer 类型的父类）
     */
    public static void showKeyValue2(Generic<? super Integer> obj) {
        System.out.println("key == " + obj.getKey());
    }

    /**
     * public <T> T showKeyName(Generic<T extends Number> container)，编译器会报错："Unexpected bound"
     */
    public <T extends Number> T showKeyName(Generic<T> container){
        System.out.println("container key :" + container.getKey());
        T test = container.getKey();
        return test;
    }

    public static void main(String[] args) {
        Generic<String> generic1 = new Generic<>("123");
        Generic<Integer> generic2 = new Generic<>(996);
        Generic<Float> generic3 = new Generic<Float>(2.4f);
        Generic<Double> generic4 = new Generic<Double>(6.18);

        Generic<Number> generic5 = new Generic<>(911);
        Generic<Boolean> generic6 = new Generic<>(true);

        //这一行代码编译器会提示错误，因为String类型并不是Number类型的子类
        //showKeyValue1(generic1);
        Limit.showKeyValue1(generic2);
        Limit.showKeyValue1(generic3);
        Limit.showKeyValue1(generic4);

        //这一行代码编译器会提示错误，因为Boolean类型并不是Integer类型的父类
        //showKeyValue2(generic6);
        Limit.showKeyValue2(generic5);

        System.out.println("-------------------------------------------------------------");
        Limit limit = new Limit();
        limit.showKeyName(new Generic<Integer>(666));
    }
}
