package com.mofei.aop.jdkdynamic;

import org.junit.Test;

/**
 * @author mofei
 * @date 2020/9/9 14:43
 */
public class JDKDynamicTest {
    @Test
    public void testJDKDynamicProxy() {
        ArithmeticCalculator calculator = new ArithmeticCalculatorImpl();
        JDKDynamicProxy proxy = new JDKDynamicProxy();
        ArithmeticCalculator arithmeticCalculatorProxy = (ArithmeticCalculator) proxy.getProxy(calculator);
        int add = arithmeticCalculatorProxy.add(1, 2);


    }

    @Test
    public void testCGLIBDynamicProxy() {
        ArithmeticCalculator calculator = new ArithmeticCalculatorImpl();
        CGLIBDynamicProxy cglibProxy = new CGLIBDynamicProxy(calculator);
        ArithmeticCalculator proxy = (ArithmeticCalculator) cglibProxy.getProxy();
        proxy.add(1, 2);
    }
}
