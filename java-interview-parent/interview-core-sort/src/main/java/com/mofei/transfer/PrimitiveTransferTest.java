package com.mofei.transfer;

import org.junit.Test;

/**
 * @author mofei
 * @date 2019/1/19 8:39
 * java 方法到底是值传递还是方法传递呢?
 * 首先明确是值传递
 * 1.传递的是基本参数
 * 2.传递的是引用类型
 */
public class PrimitiveTransferTest {
    @Test
    public void test01() {
        int a = 6;
        int b = 9;
        swap(a, b);
        System.out.println("交换后  a的值 " + a + ", b的值是: " + b);
    }

    private void swap(int a, int b) {
        int temp;
        temp = a;
        a = b;
        b = temp;
        System.out.println("swap()方法中a的值 " + a + ", b的值是: " + b);
    }

    private void swap(Swap s) {
        int temp;
        temp = s.a;
        s.a = s.b;
        s.b = temp;
        System.out.println("swap()方法中a的值 " + s.a + ", b的值是: " + s.b);
        s = null;

    }

    @Test
    public void fun02() {
        Swap swap = new Swap();
        swap.a = 6;
        swap.b = 9;
        swap(swap);
        System.out.println("swap()交换后 方法中a的值 " + swap.a + ", b的值是: " + swap.b);

    }

    class Swap {
        int a;
        int b;
    }

}
