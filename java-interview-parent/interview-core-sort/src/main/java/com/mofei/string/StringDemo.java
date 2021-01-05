package com.mofei.string;

import org.junit.Test;

import java.util.Arrays;

/**
 * @author com.mofei
 * @version 2019/1/18 22:54
 */
public class StringDemo {
    String test = "abcd";

    /**
     * codePointAt是返回该字符的ASCALL码
     * 下标从1开始,只有数组的从0开始,集合从0开始
     */
    @Test
    public void fun01() {
        int i = test.codePointAt(1);
        System.out.println("i = " + i);
        System.out.println("a = " + 'a');
    }

    /**
     * 字符串之间的比较,如果原始的元素相等,返回0,
     * 从第一个元素开始相比,知道所有的元素都相等才返回0.
     * 如果元素比原来的大,那么返回正数,两者之间Asscall码的差值, 比如a 比 c 大2
     */
    @Test
    public void test02() {
        int abc = test.compareTo("dcde");
        System.out.println("abc = " + abc);
    }

    @Test
    public void fun03() {
        String s = "1.1";
        String s2 = "1.2.1";
        System.out.println("s.compareTo(s2) = " + s.compareTo(s2));
    }

    /**
     * 需求,给定一本书的章节序号,进行排序,从小到大
     * 比如 1.1   ,1.2 ,1.3  , 2.3.1, 2.4.5
     */
    @Test
    public void fun04() {
        String[] testArr = {"1.1", "1.2", "1.3.5.1", "1.0.1", "5.1", "5.2"};
        for (int i = 0; i < testArr.length - 1; i++) {
            for (int j = 0; j < testArr.length - i - 1; j++) {
                String temp;
                if (testArr[j].compareTo(testArr[j + 1]) > 0) {
                    temp = testArr[j];
                    testArr[j] = testArr[j + 1];
                    testArr[j + 1] = temp;
                }
            }
        }
        System.out.println(Arrays.toString(testArr));
    }

    @Test
    public void fun05() {
        int i = "1.1".compareTo("1.01");
        int i2 = "1.2".compareTo("1.01");
        System.out.println("i = " + i);
        System.out.println("2i = " + i2);
    }

    /**
     * 将字符串"ABDGILOSOLPEL" 按字符串大小排序
     */
    @Test
    public void fun06() {
        String test = "ABDGILOSOLPEL";
        char[] chars = test.toCharArray();
        char[] newC = bubble(chars);
        String result = String.valueOf(newC);
        System.out.println("result = " + result);

    }

    private char[] bubble(char[] chars) {
        for (int i = 0; i < chars.length - 1; i++) {
            for (int j = 0; j < chars.length - i - 1; j++) {
                char temp;
                if (chars[j] > chars[j + 1]) {
                    temp = chars[j];
                    chars[j] = chars[j + 1];
                    chars[j + 1] = temp;
                }
            }
        }
        return chars;
    }

    @Test
    public void fun07() {
        String s = "abcde";
        int n = s.length();

        while (n-- != 0) {
            System.out.println("n = " + n);
        }
    }

    protected boolean equalsme(Object o) {
        if (this == o) {
            return true;
        }
        if (o instanceof String) {
            String newS = (String) o;
            //1.判断长度一致
        }
        return false;
    }

    int time = 50000;

    @Test
    public  void testString() {
        String s = "";
        long begin = System.currentTimeMillis();
        for (int i = 0; i < time; i++) {
            s += "java";
        }
        long over = System.currentTimeMillis();
        System.out.println("操作" + s.getClass().getName() + "类型使用的时间为：" + (over - begin) + "毫秒");
    }

    @Test
    public  void testStringBuilder () {
        StringBuilder s = new StringBuilder();
        long begin = System.currentTimeMillis();
        for(int i=0; i<time; i++){
            s.append("java");
        }
        long over = System.currentTimeMillis();
        System.out.println("操作"+s.getClass().getName()+"类型使用的时间为："+(over-begin)+"毫秒");
    }
}
