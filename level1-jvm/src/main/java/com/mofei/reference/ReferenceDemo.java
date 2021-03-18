package com.mofei.reference;

/**
 * 默认就是强引用
 * o2强引用.垃圾回收以后,没有被回收
 * @author mofei
 * @date 2021/3/12 0:02
 */
public class ReferenceDemo {
    public static void main(String[] args) {
        Object o = new Object();
        System.out.println("o = " + o);
        Object o2 = o;
        o = null;
        System.out.println("o2 = " + o2);
        System.gc();

        System.out.println("==================");
        System.out.println("o = " + o);
        System.out.println("o2 = " + o2);

    }
}
