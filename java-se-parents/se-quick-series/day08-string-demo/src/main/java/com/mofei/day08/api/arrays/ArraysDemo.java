package com.mofei.day08.api.arrays;

import java.util.Arrays;

/**
 * 需求:
 * 根据随意字符串,将升序排序,倒叙打印
 *
 * @author mofei
 * @date 2018/12/23 21:56
 */
public class ArraysDemo {
    public static void main(String[] args) {
        String s = "aldkieolvnclkxzpoeklfdskfjalksdjflzjsdlkfjlsdjvocv";
        char[] chars = s.toCharArray();
        System.out.println("排序前:================================================================");
        for (int i = 0; i < chars.length; i++) {
            System.out.print(", " + chars[i]);
        }
        Arrays.sort(chars);
        System.out.println();
        System.out.println("排序后:================================================================");
        for (int i = chars.length - 1; i >= 0; i--) {
            System.out.print(", " + chars[i]);
        }
    }
}
