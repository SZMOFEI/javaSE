package com.mofei.cainiao.comuilt;

/**
 * 算术运算符
 * 关系运算符
 * 位运算符
 * 逻辑运算符
 * 赋值运算符
 * 其他运算符
 *
 * @author mofei
 * @date 2021/3/6 11:14
 */
public class JavaOperatorDemo {
    public static void main(String[] args) {


        arithmeticMethod();
    }

    /**
     * + - * / % ++ --
     */
    private static void arithmeticMethod() {

        int a = 3, b = 3, c = 3, d = 3;
//        a++;
        ++a;
        System.out.println("a +b  = " + (a+b));
        ++b;
        System.out.println("b = " + b);

        --c;
        System.out.println("c = " + c);
        d--;
        System.out.println("d = " + d);
        int sum = a + b;
        int mud = sum - a;
        int cheng = a * sum;
        int mude = 1 % sum;
    }
}
