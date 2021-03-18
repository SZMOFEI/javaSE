package com.mofei.cainiao.base;

import java.io.Serializable;

/**
 * @author mofei
 * @date 2021/3/6 7:46
 */
public  class Dog implements Serializable {
    private static final long serialVersionUID = -1L;

    public Dog() {
    }

    public Dog(String name) {
        this.name = name;
    }

    public Dog(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Dog{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    private String name;
    private int age;

    void sleep() {

    }

    void run() {

    }

    void eat() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

}
