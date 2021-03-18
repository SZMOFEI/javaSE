package com.mofei.Integer;

import org.junit.Test;

/**
 * @Classname IntegerDemo
 * @Description 主要演示包装类Integer 和int 的比较
 * @version 2019/1/18 14:40
 * @Created by HELLO_MOFEI
 */
public class IntegerDemo {
    public static void main(String[] args) {
        /** 1.两个都是Integer
         *      1.1 两个都是new
         *      1.2 一个new一个没有new
         *      1.3 两个都不new
         *  2.两个都是int
         *  3.一个Integer和一个int
         *      3.1一个new出来的Integer
         *      3.2一个直接赋值的Integer
         *          3.2.a
         *              没有超过128
         *          3.2.b
         *              超过了128
         */
    }

    @Test
    public void fun01_1() throws Exception {
        Integer i = new Integer(12);
        Integer i2 = new Integer(12);
        //false
        System.out.println(i == i2);
    } @Test
    public void fun01_2() throws Exception {
        Integer i = new Integer(12);
        Integer i2 = 12;
        //false
        System.out.println(i == i2);
    }@Test
    public void fun01_3() throws Exception {
        Integer i = 128;
        Integer i2 = 128;
        //false
        System.out.println(i == i2);
    }

    /**
     * 2.两个都是int
     */
    @Test
    public void fun02() throws Exception {
        int i = 12;
        int i2 = 12;
        System.out.println(i == i2);
    }

    /**
     * new Integer 那么引用指向的是值，跟Integer.valueOf（）方法没有关系
     * @throws Exception
     */
    @Test
    public void fun03_1() throws Exception {
        Integer i = new Integer(128);
        int i2 = 128;
        //true
        System.out.println(i == i2);
    }

    /**
     * Integer i = 12   编译成的是  Integer i = Integer.intvalueOf(12)
     * @throws Exception
     */
    @Test
    public void fun03_2() throws Exception {
        Integer i = 128;
        int i2 = 128;
        //true
        System.out.println(i == i2);
    }

}
