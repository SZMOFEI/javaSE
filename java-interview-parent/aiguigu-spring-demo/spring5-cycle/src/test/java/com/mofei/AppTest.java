package com.mofei;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.junit.Assert.assertTrue;

/**
 * Unit test for simple App.
 */
public class AppTest {
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue() {
        assertTrue(true);
    }

    @Test
    public void testProperty() {
      ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring5-cycle.xml");
        Car car = context.getBean("car", Car.class);
        System.out.println("car = " + car);
        context.close();
    }


}
