package org.jdk5.generics.t_method;

import org.jdk8.stream.User;

/**
 * 泛型方法基础
 *  1、泛型类，是在实例化类的时候指明泛型的具体类型；
 *  2、泛型方法，是在调用方法的时候指明泛型的具体类型 。
 */
public class MethodBasic {
    /**
     * 泛型方法的基本介绍
     *
     * @param tClass 传入的泛型实参
     * @return T 返回值为T类型
     * 说明：
     *     1）public 与 返回值中间<T>非常重要，可以理解为声明此方法为泛型方法。
     *     2）只有声明了<T>的方法才是泛型方法，泛型类中的使用了泛型的成员方法并不是泛型方法。
     *     3）<T>表明该方法将使用泛型类型T，此时才可以在方法中使用泛型类型T。
     *     4）与泛型类的定义一样，此处T可以随便写为任意标识，常见的如T、E、K、V等形式的参数常用于表示泛型。
     */
    public <T> T getInstance(Class<T> tClass) throws IllegalAccessException, InstantiationException {
        T instance = tClass.newInstance();
        return instance;
    }

    public MethodBasic() {
        System.out.println("MethodBasic -> 无参构造方法！");
    }

    public static void main(String[] args) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
        MethodBasic methodBasic = new MethodBasic();

        Object instance = methodBasic.getInstance(Class.forName("org.jdk5.generics.t_method.MethodBasic"));
        System.out.println(instance);

        String str = methodBasic.getInstance(String.class);
        System.out.println(str.equals(""));
    }
}
