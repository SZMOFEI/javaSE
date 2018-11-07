package com.mofei.chapter5.c5_7_3InstanceOf;

/**
 * @author mofei
 * @date 2018/11/6 17:16
 */
public class InstanceOfDemo {
    public static void main(String[] args) {
            Object hello = "String";
        System.out.println("hello instanceof Object = " + hello instanceof Object);
        System.out.println("hello instanceof String = " + hello instanceof String);
        System.out.println("hello instanceof Math = " + (hello instanceof Math));
        System.out.println("hello instanceof  compa = " + hello instanceof  Comparable);
    }
}
