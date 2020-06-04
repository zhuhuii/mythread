package org.jdk5.enums;

/**
 * 枚举实现单例模式
 */
class User {
    //私有化构造函数
    private User() {
    }

    //定义一个静态枚举类
    static enum SingletonEnum {
        //创建一个枚举对象，该对象天生为单例
        INSTANCE;
        private User user;

        //私有化枚举的构造函数
        private SingletonEnum() {
            user = new User();
        }

        public User getInstnce() {
            return user;
        }
    }

    //对外暴露一个获取User对象的静态方法
    public static User getInstance() {
        return SingletonEnum.INSTANCE.getInstnce();
    }
}

class Test {
    public static void main(String[] args) {
        System.out.println(User.getInstance());
        System.out.println(User.getInstance());
        System.out.println(User.getInstance() == User.getInstance());
    }
}