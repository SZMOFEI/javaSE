package com.mofei.day08.api.string;

import java.util.Scanner;

/**
 * 需求:
 * 根据系统输入一个字符串,统计类型的次数
 * 类型的枚举有哪些:字符串,数字,大小写字母,其他
 *
 * @author mofei
 * @date 2018/12/23 22:31
 */
public class StringPratice {
    public static void main(String[] args) {
        //1.首先获取系统输入字符串
        //2,转化成字符数组
        //3.遍历数组,对每个元素进行遍历
        //4.对每个元素进行类型判断,定义四个变量记录
        //5.统计输出四个变量
        int countHight = 0;
        int countLow = 0;
        int countNo = 0;
        int countOther = 0;
        System.out.println("请输入一个字符串!");
        Scanner scanner = new Scanner(System.in);
        String next = scanner.next();
        char[] chars = next.toCharArray();
        for (char aChar : chars) {
            if ('A'<=aChar&&aChar<='Z'){
                countHight++;
            }else if ('a'<=aChar&&aChar<='z'){
                countLow++;
            }else if ('0'<=aChar&&aChar<='9'){
                countNo++;
            }else {
                countOther++;
            }
        }
        System.out.println("countHight = " + countHight);
        System.out.println("countLow = " + countLow);
        System.out.println("countNo = " + countNo);
        System.out.println("countOther = " + countOther);
    }
}
