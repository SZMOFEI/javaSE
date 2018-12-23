package com.mofei.day08.api.string;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * 需求:
 *     面试题: 书本都是有章节的,比如1 ,1.1 ,2.1,2.2
 *      让他们进行一次排序,达到从小到大的排序
 * @author mofei
 * @date 2018/12/23 18:05
 */
public class StringDemo {

    /**
     * String字符串的compareTo的规则
     * 首先比较的第一个字母是否一样:
     *      如果一样,比较字符串的长度,如果是比原始的长度长,返回长度的差值
     *      如果不一样,返回他们之间的Unicode差值
     */
    @Test
    public void test01() {
           String a = "1";
           String b = "1.1";
           String c = "1.2";
           String d = "1.3";
           String e = "2";
           String f = "2.1";
           String  g= "2.2";
           String  h= "2.3";
           String  i= "2.2.1";
           String  j= "2.4.1";
           String  k= "2.4.2";
           String  l= "2.5.1";
           String  m= "2.9.1";
           String  n= "5.9.1";
           String  o= "5.9.1.1.1.1.1.2.1.1";
           String  p= "5.9.1.1.1.1.1.2.1.1.5";

        int aC = a.compareTo(b);
        System.out.println("aC = " + aC);
        int i1 = b.compareTo(a);
        System.out.println("i1 = " + i1);

        int i2 = a.compareTo(i);
        System.out.println("i2 = " + i2);

        ArrayList<String> list = new ArrayList<String>();
        String[] arr = {"1","1.1","1.2","1.3","2","2.1","3","3.1","3.6","4.1.1","4.2.1","5.1.1.2","1.2.1.5","2.1.1.3.2","2.1.1.4.2"};
        Arrays.asList(arr);
        System.out.println("排序前");
        for (int i3 = 0; i3 < arr.length; i3++) {
            System.out.println(", "+arr[i3]);
        }
        Arrays.sort(arr);
        System.out.println("排序后");
        for (int i3 = 0; i3 < arr.length; i3++) {
            System.out.println("., "+arr[i3]);
        }
    }
}
