package com.mofei.demo04;

/**
 * @author mofei
 * @date 2018/9/23 6:45
 */
public class 数据类型转换之隐式转换 {
    public static void main(String[] args) {
        int x = 3;
        byte b = 4;
        System.out.println("b = " + (b + x));
        数据类型转换之隐式转换 clazz = new 数据类型转换之隐式转换();
        clazz.test();
    }

    public void test() {
        byte a = 3;
        byte b = 4;
        byte c = 3 + 4;
        //java 默认把波耶特提升为int,所以赋值给byte会编译报错
        //byte d = a + b;
        System.out.println("d = " + c);
    }

}
