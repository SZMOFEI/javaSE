package com.mofei;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring3-scope.xml");
//        Person person = context.getBean("person", Person.class);
        Person person = context.getBean("person2", Person.class);
        Person person2 = context.getBean("person2", Person.class);
//        System.out.println(person==person2);
    }
}
