package org.jdk5.annotation.define;

/**
 * 使用Annotation
 */
public class SimpleTest {

    @Login
    protected void doGet(){
        System.out.println("doGet方法被调用了");
    }

    @LoginField(username = "zhangsan", password = "1234")
    protected void doPost(){
        System.out.println("doPost方法被调用了");
    }

    @LoginFieldDefeat
    protected void init(){
        System.out.println("init");
    }

    @LoginFieldDefeat(username = "wang-ming-ming")
    protected void destory(){
        System.out.println("destory");
    }
}
