package com.mofei.cainiao.base;

/**
 * @author mofei
 * @date 2021/3/6 5:59
 */
public class ObjectDemo {
    public static void main(String[] args) {
        Dog dog = new Dog();
        dog.setAge(11);
        dog.setName("金毛");
        System.out.println("dog = " + dog);

        Dog dog1 = new Dog("长毛");
        System.out.println("dog1 = " + dog1);

        Dog dog2 = new Dog("黑子", 6);
        System.out.println("dog2 = " + dog2);
    }


}

