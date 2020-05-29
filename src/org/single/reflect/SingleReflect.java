package org.single.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * 用反射可以破坏单例模式
 */
public class SingleReflect {

    private static SingleReflect lazyMan;

    public static SingleReflect getInstance() {
        if (lazyMan == null) {
            lazyMan = new SingleReflect();
        }
        return lazyMan;
    }

    private SingleReflect() {
        System.out.println(Thread.currentThread().getName() + " -> LazyMan ok");
    }
}

class Test{
    public static void main(String[] args) throws IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        Class<SingleReflect> singleReflectClass = SingleReflect.class;
        Constructor<SingleReflect> declaredConstructor = singleReflectClass.getDeclaredConstructor(null);
        declaredConstructor.setAccessible(true);

        SingleReflect singleReflect1 = declaredConstructor.newInstance();
        SingleReflect singleReflect2 = declaredConstructor.newInstance();
        System.out.println(singleReflect1);
        System.out.println(singleReflect2);
    }
}
