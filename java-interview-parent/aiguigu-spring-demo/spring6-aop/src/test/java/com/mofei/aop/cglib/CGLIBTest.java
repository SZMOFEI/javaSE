package com.mofei.aop.cglib;

import org.junit.Test;

public class CGLIBTest {
    @Test
    public void testCGLIB() {
        CGLIBProxy cglibProxy = new CGLIBProxy(new ArithmeticCalculatorImpl());
        ArithmeticCalculator proxy = (ArithmeticCalculator) cglibProxy.getProxy(new ArithmeticCalculatorImpl());
        int add = proxy.add(1, 2);
    }
}
