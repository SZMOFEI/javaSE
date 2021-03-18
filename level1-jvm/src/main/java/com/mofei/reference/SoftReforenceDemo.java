package com.mofei.reference;

import java.lang.ref.SoftReference;

/**
 * 弱引用, 内存够用的时候,不会被回收, 如果不够的时候,会被回收
 * @author mofei
 * @date 2021/3/12 0:05
 */
public class SoftReforenceDemo {
    public static void main(String[] args) {
        Object o1 = new Object();
        SoftReference<Object> softReference = new SoftReference<>(o1);
        System.out.println("o1 = " + o1);
        System.out.println("softReference.get() = " + softReference.get());
        System.out.println("-==================================");



    }
}
