package com.mofei;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author mofei
 * @date 2021/3/17 22:01
 */
public class ScopeTest {
    @Test
    public void testScope() {
        //默认是单例模式的 通过两次获取Person对象,看看是否一样的
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring3-scope.xml");
        Object person2 = context.getBean("person2");
        Object person3 = context.getBean("person2");
        System.out.println("person3.equals(person2) = " + person3.equals(person2));

    }
}
