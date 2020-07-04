package org.jdk5.annotation.use;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Author {
    String username() default "zhangsan";

    String password() default "123456";

    String description() default "我是作者的默认描述信息！";
}
