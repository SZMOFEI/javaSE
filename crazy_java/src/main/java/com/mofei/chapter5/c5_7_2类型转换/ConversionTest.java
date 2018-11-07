package com.mofei.chapter5.c5_7_2类型转换;

/**
 * @author mofei
 * @date 2018/11/6 17:12
 */
public class ConversionTest {
    public static void main(String[] args) {
        double d = 3.145;
        long l = (long) d;
        System.out.println("l = " + l);
        int in = 5;
        Object b = "Stirng ";
        String str = (String) b;
        System.out.println("str = " + str);
        Object intObject = new Integer(5);
        if (intObject instanceof String) {
            String string = (String) intObject;
        }
    }
}
