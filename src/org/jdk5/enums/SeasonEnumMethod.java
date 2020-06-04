package org.jdk5.enums;

/**
 * 枚举常用方法
 *  toString()：返回枚举常量的名称
 *  values(): 返回枚举类型的对象数组
 *  valueof(String name) :根据名称获取枚举类中定义的常量值;要求字符串跟枚举的常量名必须一致;
 */
public class SeasonEnumMethod {
    public static void main(String[] args) {
        values();
        valueOf();
        print();
    }

    private static void values() {
        SeasonEnum[] values = SeasonEnum.values();
        for (SeasonEnum value : values) {
            System.out.println(value);
        }
    }

    private static void valueOf() {
        SeasonEnum spring = SeasonEnum.valueOf("SPRING");
        System.out.println(spring);

        SeasonEnum seasonEnum = SeasonEnum.valueOf(SeasonEnum.class, "SUMMER");
        System.out.println(seasonEnum);

        // 区分大小写，没有找到就会报错：No enum constant org.jdk5.enums.SeasonEnum.spring
    }

    private static void print() {
        System.out.println(Season.WINTER.toString());
    }
}
