package com.mofei.chapter5.c5_9静态初始化;

/**
 * @author mofei
 * @date 2018/11/17 19:35
 */
public class Root {
    static {
        System.out.println("Root 静态初始化块 ");
    }
    {
        System.out.println("Root 普通代码块初始化");
    }
}
