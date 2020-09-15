package org.example;

import org.example.single.PersonConfig;
import org.example.single.entity.Person;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author mofei
 * @date 2020/9/15 23:51
 */
public class SingleTest {
    /**
     * 测试单例的情况
     */
    @Test
    public void testSingle() {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(PersonConfig.class);
        Person person1 = ctx.getBean("person", Person.class);
        Person person2 = ctx.getBean("person", Person.class);
        //默认是单例的
        System.out.println("person2 = person1 结果是 " + (person2 == person1));
    }

    /**
     * 容器加载的时候就开始执行加载了初始化了
     */
    @Test
    public void test饿汉式() {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(PersonConfig.class);
       /* Person person1 = ctx.getBean("person", Person.class);
        Person person2 = ctx.getBean("person", Person.class);
        //默认是单例的
        System.out.println("person2 = person1 结果是 " + (person2 == person1));*/
    }

    /**
     * 只是修改了bean 为lazy加载 ,结果是true
     */
    @Test
    public void test赖加载的情况() {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(PersonConfig.class);
        Person person1 = ctx.getBean("person", Person.class);
        Person person2 = ctx.getBean("person", Person.class);
        //默认是单例的
        System.out.println("person2 = person1 结果是 " + (person2 == person1));
    }

    /**
     * 只是修改了bean 为lazy加载 ,结果是false  ,在声明Person 的时候给与了@Scope= prototype值
     */
    @Test
    public void testProtoType() {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(PersonConfig.class);
        Person person1 = ctx.getBean("person", Person.class);
        Person person2 = ctx.getBean("person", Person.class);
        //默认是单例的
        System.out.println("person2 = person1 结果是 " + (person2 == person1));
    }
}
