package org.jdk8.stream;

import java.util.List;

/**
 * 题目要求：一分钟内完成此题，只能用一行代码实现！
 * 现在有5个用户！筛选：
 * 1、ID 必须是偶数
 * 2、年龄必须大于23岁
 * 3、用户名转为大写字母
 * 4、用户名字母倒着排序
 * 5、只输出一个用户！
 */
public class UserStream {
    public static void main(String[] args) {
        List<User> users = User.getUserList();

        // 计算交给Stream流
        // lambda表达式、链式编程、函数式接口、Stream流式计算
        users.stream().filter((user) -> {
            return user.getId() % 2 == 0;
        }).filter((user) -> {
            return user.getAge() > 23;
        }).map((user) -> {
            return user.getName().toUpperCase();
        }).sorted((uid1, uid2) -> {
            return uid2.compareTo(uid1);
        }).limit(1).forEach(System.out::println);

    }
}

