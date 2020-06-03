package org.jdk5.annotation;

import java.util.ArrayList;
import java.util.List;

/**
 * Java 预留注解
 */
@SuppressWarnings("all") // 忽略指定的警告
class Animal {
    List<String> list = new ArrayList<>();

    public void eat() {
        System.out.println("animal eat method");
    }
}

class Dog extends Animal{
    /**
     * 重写 Animal的eat方法
     */
    @Override
    public void eat() {
        System.out.println("eat method");
    }

    /**
     * 定义标识该方法已过期，以后不建议使用该方法
     */
    @Deprecated
    public void go(){
    }
}
