package org.jdk5.annotation.define;

import java.lang.annotation.*;

/**
 * 元注解：
 *
 * @Retention（重要）
 * Retention 的英文意为保留期的意思。当 @Retention 应用到一个注解上的时候，它解释说明了这个注解的的存活时间。
 *  RetentionPolicy.SOURCE 注解只在源码阶段保留，在编译器进行编译时它将被丢弃忽视。
 *  RetentionPolicy.CLASS 注解只被保留到编译进行的时候，它并不会被加载到 JVM 中。
 *  RetentionPolicy.RUNTIME 注解可以保留到程序运行的时候，它会被加载进入到 JVM 中，所以在程序运行时可以获取到它们。
 *
 * @Target（重要）
 * Target 是目标的意思，@Target 指定了注解运用的地方。
 *  ElementType.ANNOTATION_TYPE 可以给一个注解进行注解
 *  ElementType.CONSTRUCTOR 可以给构造方法进行注解
 *  ElementType.FIELD 可以给属性进行注解
 *  ElementType.LOCAL_VARIABLE 可以给局部变量进行注解
 *  ElementType.METHOD 可以给方法进行注解
 *  ElementType.PACKAGE 可以给一个包进行注解
 *  ElementType.PARAMETER 可以给一个方法内的参数进行注解
 *  ElementType.TYPE 可以给一个类型进行注解，比如类、接口、枚举
 *
 * @Documented
 *  注解表明这个注解应该被 javadoc工具记录. 默认情况下,javadoc是不包括注解的。
 *  但如果声明注解时指定了 @Documented,则它会被 javadoc 之类的工具处理, 所以注解类型信息也会被包括在生成的文档中，是一个标记注解，没有成员。
 *
 * @Inherited
 * 注解过的注解进行注解的话，那么如果它的子类没有被任何注解应用的话，那么这个子类就继承了超类的注解。
 * 如：注解 Test 被 @Inherited 修饰，之后类 A 被 Test 注解，类 B 继承 A,类 B 也拥有 Test 这个注解。
 *
 * @Repeatable()
 *  Repeatable 自然是可重复的意思。@Repeatable 是 Java 1.8 才加进来的，所以算是一个新的特性。
 *  什么样的注解会多次应用呢？通常是注解的值可以同时取多个。
 *  举个例子，一个人他既是程序员又是产品经理,同时他还是个画家。
 * */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface MetaAnnotation {

    String username() default "root";

    String password() default "123456";
}
