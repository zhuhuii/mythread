package org.jdk5.reflect;

import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @Author: zhuhui
 * @Description:
 */
public class Main {

    @Test
    public void newInstance() throws IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        /**
         * 通过反射来生成对象主要有两种方式。
         */
        Class<Dog> dogClass = Dog.class;
        Dog dog1 = dogClass.newInstance();

        /**
         * 用Class对象获取无参构造Constructor对象
         */
        Constructor<Dog> constructor1 = dogClass.getConstructor();
        Constructor<Dog> constructor2 = dogClass.getConstructor(null);
        Dog dog2 = constructor1.newInstance();
        Dog dog22 = constructor2.newInstance(null);

        /**
         * 用Class对象获取指定的Constructor对象
         */
        Constructor<Dog> constructor3 = dogClass.getConstructor(String.class, String[].class);
        Dog dog3 = constructor3.newInstance("德国牧羊犬", new java.lang.String[]{"柴犬", "金毛", "哈士奇", "秋田犬"});


        System.out.println("\n----------------------------------\n");
        System.out.println("dog1：" + dog1);
        System.out.println("dog2：" + dog2);
        System.out.println("dog3：" + dog3);
        System.out.println("dog1 == dog2：" + (dog1 == dog2));
        System.out.println("dog1 .eq dog2：" + (dog1.equals(dog2)));
    }

    @Test
    public void getClassObj() throws ClassNotFoundException {
        /**
         * 获取到class对象的三种方法：
         */
        Class<Dog> objectClass = Dog.class;
        Class<? extends Dog> dogClass = new Dog().getClass();
        Class<?> driverClass = Class.forName("com.jdbc.mysql.Driver");
    }

    @Test
    public void getConstructor() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        /**
         * 获取到对象构造方法：
         */
        Class<Dog> dogClass = Dog.class;
        Constructor<Dog> constructor = dogClass.getConstructor();
        Constructor<Dog> constructor1 = dogClass.getConstructor(String.class, String[].class);

        /**
         * 用构造方法创建对象
         */
        Dog dog1 = constructor.newInstance();
        Dog dog2 = constructor1.newInstance("哈士奇", new String[]{"拆家"});
        System.out.println("dog1：" + dog1);
        System.out.println("dog2：" + dog2);
    }

    @Test
    public void getMethods() {
        Class<Dog> dogClass = Dog.class;

        // getDeclaredMethods 方法返回类或接口声明的所有方法，包括公共、保护、默认（包）访问和私有方法，但不包括继承的方法。
        Method[] declaredMethods = dogClass.getDeclaredMethods();
        for (Method method : declaredMethods) {
            System.out.println(method);
        }

        System.out.println("------------------------------------------------------------------");

        // getMethods 方法返回某个类的所有公用（public）方法，包括其继承类的公用方法。
        Method[] methods = dogClass.getMethods();
        for (Method method : methods) {
            System.out.println(method);
        }
    }

    @Test
    public void getFields() {
        /**
         * getFiled：访问公有的成员变量，（包含：本类+父类）。
         * getDeclaredField：所有已声明的成员变量，但不能得到其父类的成员变量
         */
        Class<Dog> dogClass = Dog.class;
        Field[] fields = dogClass.getFields();
        Field[] declaredFields = dogClass.getDeclaredFields();
    }

    @Test
    public void executeMethod() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, InstantiationException {
        /**
         * 执行方法
         **/
        Class<Dog> dogClass = Dog.class;
        Method toString = dogClass.getMethod("toString");
        Object result = toString.invoke(dogClass.newInstance());
        System.out.println(result);

        Method gnaw = dogClass.getDeclaredMethod("gnaw");
        gnaw.setAccessible(true);
        //Class Main can not access a member of class Dog with modifiers "private"
        gnaw.invoke(dogClass.newInstance());
    }

    @Test
    public void invokePrivateMethod() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Constructor<Dog> constructor = Dog.class.getDeclaredConstructor(String.class, Integer.class, Float.class, String.class, String[].class);
        constructor.setAccessible(true);

        Dog dog1 = constructor.newInstance("旺财", 6, 12.3f, "哈士奇", new String[]{"拆机", "捣乱"});
        Dog dog2 = new Dog("旺财", 6, 12.3f, "哈士奇", new String[]{"拆机", "捣乱"});

        System.out.println(dog1.toString());
        System.out.println(dog2.toString());

        System.out.println("(dog1 == dog2) ==> " + (dog1 == dog2));
        System.out.println("(dog1 .eq dog2) ==> " + dog1.equals(dog2));
    }
}
