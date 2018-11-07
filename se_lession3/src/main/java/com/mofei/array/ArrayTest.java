package com.mofei.array;

import java.util.Arrays;

/**
 * @author mofei
 * @date 2018/11/5 21:41
 */
public class ArrayTest {
    private String[] hanStr = {"零", "壹", "贰", "弎", "肆", "伍", "陆", "柒", "捌", "玖"};
    private String[] unitStr = {"分","角","十", "百", "千", "万", "十万", "百万", "千万", "亿"};
    //需求是将一个数字转成中文的汉字
    //1.将一个double的数字转成字符串数组
    //2.将一个数组转成String输出

    public static String[] devide(double d) {
        //1.整数部分直接强转成long
        long zheng = (long) d;
        //2.小数部分是乘以100向上取整
        long xiao = Math.round((d - zheng)*100);
        //3.转成字符串的形式有两种
        return new String[]{zheng + "", String.valueOf(xiao)};
    }

    /**
     * 将数字转化成字符串返回
     *
     * @param num 需要转化的数组
     * @return 返利中文繁体
     */
    public String toHanStr(String num) {
        String result = "";
        int leng = num.length();
        //leng = 4  单位的索引应该是2
        //6430
        for (int i = 0; i < leng; i++) {
            int number = num.charAt(i) - 48;
            if (i != leng - 1 && number != 0) {

                result += hanStr[number] + unitStr[leng  - i];
            } else if (i==leng-1&&number==0) {
                continue;
            }else {

                    result += hanStr[number];

            }
        }
        //如果字符串匹配到1个0以上结尾,则不进行补零

        String s = result.replaceAll("[零]+", "零");
        return s+"元";
    }  /**
     * 将数字转化成字符串返回
     *
     * @param num 需要转化的数组
     * @return 返利中文繁体
     */
    public String toHanStr2(String num) {
        String result = "";
        int leng = num.length();
        //leng = 4  单位的索引应该是2

        //6430或者
        for (int i = 0; i < leng; i++) {
            int number = num.charAt(i) - 48;
            if ( number != 0) {

                result += hanStr[number] + unitStr[leng  -1- i];
            }
        }
        return result;
    }

    public static void main(String[] args) {
        ArrayTest arrayTest = new ArrayTest();
        String s = arrayTest.toHanStr("16450");
        System.out.println("s = " + s);
        String[] devide = devide(1006.333);
        System.out.println("Arrays.toString(devide) = " + Arrays.toString(devide));
        String zhengShu = arrayTest.toHanStr(devide[0]);
        String 小数 = arrayTest.toHanStr2(devide[1]);
        System.out.println("zhengShu+小数 = " + zhengShu +","+ 小数);
    }

}
