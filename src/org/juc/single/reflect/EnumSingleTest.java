package org.juc.single.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * @author zhuhui
 * @date 2020/5/30 0:03
 */
public enum EnumSingleTest {
    INSTANCE;

    public static EnumSingleTest getInstance() {
        return EnumSingleTest.INSTANCE;
    }
}

class test {

    /**
     * Cannot reflectively create enum objects
     */
    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Class<EnumSingleTest> enumSingleTestClass = EnumSingleTest.class;
        Constructor<EnumSingleTest> constructor = enumSingleTestClass.getDeclaredConstructor(String.class,int.class);
        constructor.setAccessible(true);
        EnumSingleTest enumSingle = constructor.newInstance();
        System.out.println(enumSingle);
    }
}
