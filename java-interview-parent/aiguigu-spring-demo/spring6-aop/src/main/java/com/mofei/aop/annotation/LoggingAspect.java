package com.mofei.aop.annotation;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

//声明一个IOC容器的bean
@Component
//声明是一个切面 ，可以集中声明横切功能的声明， 比如日志功能 ， 验证参数的切面
@Aspect
public class LoggingAspect {
    /**
     * @Before 就是一个前置通知
     * apsect 表达式， execution开头
     * public 代表方法级别
     * int 方法返回值
     * add 表示某个包下的方法
     * int int 代表参数列表
     */
    @Before(value = "execution(public int com.mofei.aop.annotation.ArithmeticCalculatorImpl.add(int ,int))")
    public void before(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        List<Object> objects = Arrays.asList(args);
        System.out.println(" 执行 " + methodName + " 方法之前，参数是 " + objects);
    }

    @After(value = "execution(public int com.mofei.aop.annotation.ArithmeticCalculatorImpl.add(int ,int))")
    public void after(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        System.out.println(" 执行 " + methodName + " 方法之后");
    }
}
