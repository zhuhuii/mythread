package org.jdk5.generics.t_class;

import java.util.*;

/**
 * 泛型类
 */
class Demo {
    public static void main(String[] args) {
        GenericClass<String> stringGenericClass = new GenericClass<>("zhangSan");
        GenericClass<Integer> integerGenericClass = new GenericClass<>(1024);
        System.out.println(stringGenericClass.getKey());
        System.out.println(integerGenericClass.getKey());

        errorExample();
    }

    /**
     * 不传入泛型类型实参的话，在泛型类中使用泛型的方法或成员变量定义的类型可以为任何的类型。
     */
    private static void errorExample() {
        GenericClass generic1 = new GenericClass("111111");
        GenericClass generic2 = new GenericClass(4444);
        GenericClass generic3 = new GenericClass(55.55);
        GenericClass generic4 = new GenericClass(false);
        // 上述操作类似于
        List list = new ArrayList();
        Map map = new HashMap();
        Set set = new HashSet();

        System.out.println("泛型测试key => " + generic1.getKey());
        System.out.println("泛型测试key => " + generic2.getKey());
        System.out.println("泛型测试key => " + generic3.getKey());
        System.out.println("泛型测试key => " + generic4.getKey());
    }
}


