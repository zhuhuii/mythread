package org.jdk5.enums;

import org.junit.Test;

/**
 * 枚举常用方法
 *  toString()：返回枚举常量的名称
 *  values(): 返回枚举类型的对象数组
 *  valueof(String name) :根据名称获取枚举类中定义的常量值;要求字符串跟枚举的常量名必须一致;
 */
public class SeasonEnumMethod {
    @Test
    public void values() throws Exception {
        SeasonEnum[] values = SeasonEnum.values();
        for (SeasonEnum value : values) {
            System.out.println(value);
        }

        String name = "秋天123";
        SeasonEnum season = getByValue(name);
        System.out.println(season.toString());
    }

    private SeasonEnum getByValue(String name) throws Exception {
        for (SeasonEnum seasonEnum : SeasonEnum.values()) {
            if (seasonEnum.getSEASONNAME().equals(name)) {
                return seasonEnum;
            }
        }
        throw new Exception("没有找到对应的枚举");
    }

    @Test
    public void valueOf() {
        // 区分大小写，没有找到就会报错：No enum constant org.jdk5.enums.SeasonEnum.spring
        SeasonEnum spring = SeasonEnum.valueOf("SPRING");
        System.out.println(spring);

        SeasonEnum seasonEnum = SeasonEnum.valueOf(SeasonEnum.class, "SUMMER");
        System.out.println(seasonEnum);
    }
}
