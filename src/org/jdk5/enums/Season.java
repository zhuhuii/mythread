package org.jdk5.enums;

/**
 * 自定义枚举类
 */
public class Season {

    // 1. 自定义枚举类对象的属性不应该允许被修改，所有应该使用private final修饰
    private final String SEASONNAME;
    private final String SEASONDESC;

    // 2. 私有化类的构造器，并给对象属性赋值
    private Season(String seasonName, String seasonDesc) {
        this.SEASONNAME = seasonName;
        this.SEASONDESC = seasonDesc;
    }

    // 3. 提供当前枚举类的多个对象
    public static final Season SPRING = new Season("春天", "春暖花开");
    public static final Season SUMMER = new Season("夏天", "夏日炎炎");
    public static final Season AUTUMN = new Season("秋天", "秋高气爽");
    public static final Season WINTER = new Season("冬天", "白雪皑皑");

    public String getSEASONNAME() {
        return SEASONNAME;
    }

    public String getSEASONDESC() {
        return SEASONDESC;
    }

    @Override
    public String toString() {
        return "Season{" +
                "SEASONNAME='" + SEASONNAME + '\'' +
                ", SEASONDESC='" + SEASONDESC + '\'' +
                '}';
    }
}
