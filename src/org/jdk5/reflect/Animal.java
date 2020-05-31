package org.jdk5.reflect;

import java.util.Objects;

/**
 * @Author: zhuhui
 * @Description:
 */
public abstract class Animal {
    public String name;
    public Integer age;
    public Float weight; //体重

    public Animal() {
        System.out.println("super -> 父类无参构造");
    }

    public Animal(String name, Integer age, Float weight) {
        this.name = name;
        this.age = age;
        this.weight = weight;
        System.out.println("super -> 父类有参构造方法");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Animal)) return false;
        Animal animal = (Animal) o;
        return Objects.equals(name, animal.name) &&
                Objects.equals(age, animal.age) &&
                Objects.equals(weight, animal.weight);
    }
}
