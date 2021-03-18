package com.mofei.singleTon;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * 单例模式的测试：
 * 使用的是懒汉式写法，因为构造函数私有化，所以无法通过new的形式实例化一个构造函数
 */
public class Demo02Test {
    public static void main(String[] args) {
        SingleTonDemo02 tonDemo1 = SingleTonDemo02.getSingleTonDemo();
        SingleTonDemo02 tonDemo3 = SingleTonDemo02.getSingleTonDemo();
        SingleTonDemo02 tonDemo2 = SingleTonDemo02.getSingleTonDemo();

        System.out.println("tonDemo1 = " + tonDemo1.equals(tonDemo2));
        System.out.println("tonDemo1 = " + tonDemo1.equals(tonDemo3));
        System.out.println("tonDemo1 = " + tonDemo2.equals(tonDemo3));

    }
}
