package org.jdk5.annotation.define;

/**
 * 自定义注解和属性默认值
 *  1、注解只有成员变量，没有方法。
 *  2、注解的成员变量在注解的定义中以“无形参的方法”形式来声明
 *  3、注解中定义属性时它的类型必须是 8 种基本数据类型外加 类、接口、注解及它们的数组。
 */
public @interface LoginFieldDefeat {

    // 默认值需要用 default 关键值指定
    String username() default "root";
    String password() default "123456";
}
