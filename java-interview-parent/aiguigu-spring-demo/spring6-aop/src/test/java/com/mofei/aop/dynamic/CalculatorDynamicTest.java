package com.mofei.aop.dynamic;

import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class CalculatorDynamicTest {

    @Test
    public void testDynamic() {
        ArithmeticCalculator arithmeticCalculator = new ArithmeticCalculatorImpl();
        JDKProxy jdkProxy = new JDKProxy(arithmeticCalculator);
        ArithmeticCalculator proxy = (ArithmeticCalculator) jdkProxy.getProxy(arithmeticCalculator);
        int add = proxy.add(1, 2);
        int mul = proxy.mul(1, 2);
        int sub = proxy.sub(3, 2);
        int div = proxy.div(10, 2);
    }

    @Test
    public void testCalculator() {
        ArithmeticCalculator calculator = new ArithmeticCalculatorImpl();
        int add = calculator.add(1, 2);
        int sub = calculator.sub(5, 2);
        int mul = calculator.mul(5, 2);
        int div = calculator.div(20, 2);
    }
}
