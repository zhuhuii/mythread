package org.jdk5.generics;

import java.util.ArrayList;
import java.util.List;

/**
 * 1、为什么需要泛型
 * 2、泛型参数只能代表引用型类型，不能是原始类型（像int,double,char的等）。
 * 3、泛型的特性：泛型只在编译阶段有效。
 *
 * 4、泛型类
 * 5、泛型接口
 * 6、泛型方法
 * 7、泛型通配符
 * 8、泛型上下限
 *
 * https://www.cnblogs.com/coprince/p/8603492.html
 * https://blog.csdn.net/s10461/article/details/53941091
 */
class Demo {
    public static void main(String[] args) {
        Demo instance = new Demo();

        try {
            instance.test01();
        } catch (Exception e) {
            e.printStackTrace();
        }
        instance.test02();
        instance.test03();
    }

    /**
     * 一个被举了无数次的例子
     */
    private void test01() {
        List list = new ArrayList();
        list.add("aaa");
        list.add(123);

        for (int i = 0; i < list.size(); i++) {
            /**
             * java.lang.Integer cannot be cast to java.lang.String
             */
            String str = (String) list.get(i);
            System.out.println("测试：item = " + str);
        }
    }

    /**
     * 使用了泛型之后
     */
    private void test02() {
        List<String> list = new ArrayList<>();
        list.add("aaa");
        list.add("bbb");
        list.add("ccc");

        list.forEach(item -> System.out.println(item));
    }

    private void test03() {
        List<String> stringsList = new ArrayList<>();
        List<Integer> integerList = new ArrayList<>();

        Class<? extends List> stringListClass = stringsList.getClass();
        Class<? extends List> integerListClass = integerList.getClass();

        if (stringListClass.equals(integerListClass)){
            System.out.println("泛型类型相同");
        }
    }
}
