package org.jdk5.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Method;

/**
 * https://blog.csdn.net/qq1404510094/article/details/80577555
 *
 * 1、自定义Annotation
 * 2、元注解：@Retention、@Target、@Documented、@Inherited、@Repeatable 5 种。
 * 3、Java 预置的注解：@Override、@Deprecated、@SuppressWarnings
 * 4、注解的提取
 */
public class AnnotationBasic {
    // 5、注解的使用场景，javaWeb框架中大量的注解
    public static void main(String[] args) {
        NoBug noBug = new NoBug();
        Method[] declaredMethods = noBug.getClass().getDeclaredMethods();
        for (Method declaredMethod : declaredMethods) {
            declaredMethod.setAccessible(true);
            boolean annotationPresent = declaredMethod.isAnnotationPresent(Check.class);
            if (annotationPresent) {
                try {
                    Object invoke = declaredMethod.invoke(noBug, null);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@interface Check {
}

class NoBug {
    @Check
    public void print() {
        System.out.println("1234567890");
    }

    @Check
    public void jiafa() {
        System.out.println("1+1=" + 1 + 1);
    }

    @Check
    public void jiefa() {
        System.out.println("1-1=" + (1 - 1));
    }

    @Check
    public void chengfa() {
        System.out.println("3 x 5=" + 3 * 5);
    }

    @Check
    public void chufa() {
        System.out.println("6 / 0=" + 6 / 0);
    }

    @Check
    public void end() {
        System.out.println("我写的程序没有 bug!");
    }
}



