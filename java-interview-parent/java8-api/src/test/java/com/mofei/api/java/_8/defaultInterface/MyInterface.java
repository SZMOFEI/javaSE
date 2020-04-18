package com.mofei.api.java._8.defaultInterface;

/**
 * JDK8的新特性
 * 1. 默认接口方法
 * 2. lambada表达式
 * 3. stream流式编程
 * 4. 新日期API
 */
public interface MyInterface {
    public double calculate(int a);

    default double sqrt(double a) {
        return Math.sqrt(a);
    }
}
