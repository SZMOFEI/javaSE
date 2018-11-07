package com.mofei.demo03;


import com.mofei.demo03.reforence.User;

import java.math.BigDecimal;
import java.util.*;

/**
 * @author mofei
 * @date 2018/9/22 7:51
 */
public class 变量的概述及格式 {
    public static void main(String[] args) {
        //testBaseDataType();

        //2.定义四种引用变量

        String s = "hello String ";
        System.out.println("字符串s = " + s);
        int[] arr = {1,2,3,4,5};
        for (int i : arr) {
            System.out.println("i = " + i);
        }
        List<int[]> list = Arrays.asList(arr);
        Map map = new HashMap<Integer,String>();
        map.put(1,"陈工");
        map.put(2,"李工");
        map.put(3,"秦工");
        System.out.println("map = " + map.get(1));

        User user = new User();
        user.setAge(15);
        System.out.println("user = " + user);
    }


    private static void testBaseDataType() {
        //1.定义八种基本变量
        int a = 12000;
        byte b = 1;
        short s = 30000;
        float f  = 7.8f;
        double d = 270000000;
        long l = 779999L;
        boolean bo = true;
        char c = 'c';

        System.out.println(a);
        System.out.println(b);
        System.out.println(s);
        System.out.println(f);
        System.out.println(d);
        System.out.println(l);
        System.out.println(bo);
        System.out.println(c);
        //-----------包装类型练习--------------
        System.out.println("--------------包装数据类型变量定义---------------------------------");

        Integer i = 55;
        Byte bt = 10;
        Short st = 30000;
        Long lg = 100000000000000000L;
        Character ca  = 't';
        Boolean boo =false;
        Float ft = 567.88F;
        Double db = 10000000000000000000000000000000000000000000000000000000000D;

        System.out.println(i);
        System.out.println(bt);
        System.out.println(st);
        System.out.println(lg);
        System.out.println(ca);
        System.out.println(boo);
        System.out.println(ft);
        System.out.println(db);

        //-----------最大值练习--------------
        System.out.println("========================最大值练习===================");
        System.out.println("Integer.MAX_VALUE = " + Integer.MAX_VALUE);
        System.out.println("Integer.MIN_VALUE = " + Integer.MIN_VALUE);
        System.out.println("Byte.MAX_VALUE = " + Byte.MAX_VALUE);
        System.out.println("Byte.MIN_VALUE = " + Byte.MIN_VALUE);
        System.out.println("Short.MAX_VALUE = " + Short.MAX_VALUE);
        System.out.println("Short.MIN_VALUE = " + Short.MIN_VALUE);

        System.out.println("Float.MAX_VALUE = " + Float.MAX_VALUE);
        System.out.println("Float.MIN_VALUE = " + Float.MIN_VALUE);
        System.out.println("Long.MAX_VALUE = " + Long.MAX_VALUE);
        System.out.println("Long.MIN_VALUE = " + Long.MIN_VALUE);
        System.out.println("Double.MAX_VALUE = " + Double.MAX_VALUE);
        System.out.println("Double.MIN_VALUE = " + Double.MIN_VALUE);

        System.out.println("Character.MAX_VALUE = " + Character.MAX_VALUE);
        System.out.println("Character.MIN_VALUE = " + Character.MIN_VALUE);

        //扩展常用的金额数
        BigDecimal decimal = new BigDecimal(10);
    }
}
