package com.mofei;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author mofei
 * @date 2021/3/16 23:11
 */
public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("bean.xml");
        Object person = context.getBean("person");
        System.out.println("person = " + person);
    }
}
