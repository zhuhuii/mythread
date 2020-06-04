package org.jdk8.lambda;

import org.jdk8.stream.User;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/**
 * JDK1.5
 * 泛型、枚举、反射、注解
 *
 * JDK1.8
 * lambda表达式、链式编程、函数式接口、Stream流式计算
 *
 * 超级多FunctionalInterface
 * 简化编程模型，在新版本的框架底层大量应用！
 * foreach(消费者类的函数式接口)
 */
public class Demo {
    public static void main(String[] args) {
        Demo demo1 = new Demo();
//        demo1.newThread();
//        demo1.newforeach();
//        demo1.newStream();
        demo1.lambdaCollect();
    }

    private void lambdaBase() {
        // 1. 不需要参数,返回值为 5
        Supplier<Integer> c = () -> 5;

        // 2. 接收一个参数(数字类型),返回其2倍的值
        Function<Integer, Integer> function1 = num -> 2 * num;
        Function<Integer, Integer> function2 = (num) -> 2 * num;
        Function<Integer, Integer> function3 = (num) -> {
            return 2 * num;
        };

        // 3. 接受2个参数(数字),并返回他们的差值
        Comparator<Integer> comparator = (x, y) -> x - y;

        // 4. 接收2个int型整数,返回他们的和
        Comparator<Integer> comparator2 = (x, y) -> x + y;

        // 5. 接受一个 string 对象,并在控制台打印,不返回任何值(看起来像是返回void)
        Consumer<String> consumer0 = System.out::println;
        Consumer<String> consumer1 = str -> System.out.println(str);
        Consumer<String> consumer2 = str -> {
            System.out.println(str);
        };
        Consumer<String> consumer3 = (str) -> {
            System.out.println(str);
        };
        Consumer<String> consumer4 = (String str) -> {
            System.out.println(str);
        };
    }

    /**
     * ()->就可以代替整个匿名内部类！
     */
    private void newThread() {
        //before java8
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("匿名内部类 实现线程");
            }
        }).start();

        //after java8
        new Thread(() -> {
            System.out.println("java8 lambda实现线程");
        }).start();
    }

    /**
     * lambda foreach
     */
    private void newforeach() {
        List<String> languages = Arrays.asList("java", "scala", "python");
        //before java8
        for (String item : languages) {
            System.out.println("before java8：" + item);
        }

        //after java8
        languages.forEach(x -> System.out.println(x));  // 简化版
        languages.forEach((String x) -> System.out.println("lambda原始版：" + x));
    }

    /**
     * lambda java.util.stream
     */
    private void newStream() {
        List<User> userList = User.getUserList();

        // 年龄等于25的,或者编号小于3的
        // id 倒叙排序
        Predicate<User> agePredicate = user -> user.getAge() == 25;
        Predicate<User> idPredicate = user -> user.getId() < 3;

        userList.stream()
                .filter(agePredicate.or(idPredicate))
                .sorted((u1, u2) -> u2.getId().compareTo(u1.getId()))
                .limit(2)
                .forEach(user -> System.out.println(user));
    }

    /**
     * lambda collectors
     */
    private void lambdaCollect() {
        List<User> userList = User.getUserList();

        // .map() 将对象进行转换
        System.out.println("修改前");
        userList.forEach(user -> System.out.println(user));
        System.out.println("修改后");
        userList.stream().map(user -> {
            user.setAge(user.getAge() + 100);
            return user;
        }).forEach(user -> System.out.println(user));

        // 流API的 reduce() 方法将所有数字合成一个
        Integer ageSum = userList.stream().map(user -> user.getAge()).reduce((sum, user) -> sum + user).get();
        System.out.println("修改后年龄总和" + ageSum);


        System.out.println("==========================================================================");


        //排序过滤等一系列操作之后的元素 放入新的list
        userList = userList.stream().filter(user -> user.getAge() < 125).collect(Collectors.toList());
        userList.forEach(user -> System.out.println("年龄小于125的用户：" + user));

        //将 name 属性用" , "，连接拼接成一个字符串
        String names = userList.stream().map(user -> user.getName()).collect(Collectors.joining(","));
        System.out.println(names);

        //将name 放入到新的 set 集合中
        Set<String> nameSet = userList.stream().map(user -> user.getName()).collect(Collectors.toSet());
        nameSet.forEach(name -> System.out.println(name));

        System.out.println("");
        System.out.println("------list转map--------");
        Map<Integer, User> usersMap = userList.stream().collect(Collectors.toMap(u -> u.getId(), u -> u));
        usersMap.forEach((key, value) -> System.out.println(key + "：" + value));

        // summaryStatistics 计算集合元素的最大、最小、平均等值
        IntSummaryStatistics intSummaryStatistics = userList.stream().mapToInt(user -> user.getId()).summaryStatistics();
        System.out.println("最大值：" + intSummaryStatistics.getMax());
        System.out.println("最小值：" + intSummaryStatistics.getMin());
        System.out.println("平均值：" + intSummaryStatistics.getAverage());
        System.out.println("总和：" + intSummaryStatistics.getSum());
        System.out.println("个数：" + intSummaryStatistics.getCount());
    }
}
