package com.mofei;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.sql.DataSource;

import static org.junit.Assert.assertNotNull;
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
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring3-scope.xml");
        DataSource dataSource = context.getBean("dataSource", DataSource.class);
        assertNotNull(dataSource);
    }

    @Test
    public void testExternalProperty() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring3-scope.xml");
        DataSource dataSource = context.getBean("dataSource2", DataSource.class);
        assertNotNull(dataSource);
        System.out.println("dataSource.toString() = " + dataSource.toString());
    }
}
