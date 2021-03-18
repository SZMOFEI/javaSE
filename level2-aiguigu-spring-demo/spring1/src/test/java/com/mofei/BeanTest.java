package com.mofei;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author mofei
 * @date 2021/3/17 21:51
 */
public class BeanTest {
    @Test
    public void name() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("bean-car.xml");
        Object car = context.getBean("car");
        System.out.println("car = " + car);
    }
}
