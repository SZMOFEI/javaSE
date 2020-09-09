package com.mofei.aop.annotaion;

import com.mofei.aop.annotation.ArithmeticCalculator;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringAopAnnotationTest {

    private ApplicationContext ctx = null;
    private ArithmeticCalculator calculatorImpl = null;


    {
        ctx = new ClassPathXmlApplicationContext("spring-aop.xml");
        calculatorImpl = ctx.getBean("arithmeticCalculatorImpl", ArithmeticCalculator.class);
    }

    @Test
    public void testAspect() {
       calculatorImpl.add(1, -2);
        calculatorImpl.div(20, 2);
    }
}
