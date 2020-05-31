package org.jdk5.reflect;

import java.util.Arrays;
import java.util.Objects;

/**
 * @Author: zhuhui
 * @Description:
 */
public class Dog extends Animal {
    /**
     * 品种
     **/
    private String breed;
    /**
     * 爱好
     **/
    private String[] hobbys;

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public String[] getHobbys() {
        return hobbys;
    }

    public void setHobbys(String[] hobbys) {
        this.hobbys = hobbys;
    }

    //啃骨头
    private void gnaw() {
        System.out.println("Animal gnaw（啃骨头）..");
    }

    //喝汤
    protected void eat() {
        System.out.println("Animal eat（喝汤）...");
    }

    //咬人
    public void bite() {
        System.out.println("Animal bite（咬人）...");
    }


    public Dog() {
        System.out.println("this -> 子类无参构造方法\n");
    }

    public Dog(String breed, String[] hobbys) {
        this.breed = breed;
        this.hobbys = hobbys;
        System.out.println(String.format("this -> 子类有参构造方法 --> { breed:%s , hobbys:%s }", breed, hobbys.toString()));
    }

    protected Dog(String name, Integer age, Float weight, String breed, String[] hobbys) {
        super(name,age,weight);
        this.breed = breed;
        this.hobbys = hobbys;
        System.out.println("this -> 全部参数...\n");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Dog)) return false;
        Dog dog = (Dog) o;
        return Objects.equals(getBreed(), dog.getBreed()) &&
                Arrays.equals(getHobbys(), dog.getHobbys()) && super.equals(o);
    }

    @Override
    public String toString() {
        return "Dog{" +
                "breed='" + breed + '\'' +
                ", hobbys=" + Arrays.toString(hobbys) +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", weight=" + weight +
                '}';
    }
}
