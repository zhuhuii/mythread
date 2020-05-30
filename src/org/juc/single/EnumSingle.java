package org.juc.single;

/**
 * @author zhuhui
 * @date 2020/5/30 0:03
 */
public enum EnumSingle {
    INSTANCE;

    //doSomething 该实例支持的行为

    //可以省略此方法，通过Singleton.INSTANCE进行操作
    public static EnumSingle getInstance() {
        return EnumSingle.INSTANCE;
    }
}
