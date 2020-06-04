package org.jdk5.enums;

/**
 * enum枚举
 */
public enum  SeasonEnum {

    // 提供当前枚举的对象, 逗号隔开
    SPRING("春天","春暖花开"),
    SUMMER("夏天","夏日炎炎"),
    AUTUMN("秋天","秋高气爽"),
    WINTER("冬天","白雪皑皑");

    // 1. 自定义枚举类对象的属性不应该允许被修改，所有应该使用private final修饰
    private final String SEASONNAME;
    private final String SEASONDESC;

    // 2. 私有化类的构造器，并给对象属性赋值
    private SeasonEnum(String seasonName, String seasonDesc) {
        this.SEASONNAME = seasonName;
        this.SEASONDESC = seasonDesc;
    }

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
