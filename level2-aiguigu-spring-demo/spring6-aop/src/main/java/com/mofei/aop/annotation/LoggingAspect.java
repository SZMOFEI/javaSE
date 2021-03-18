package com.mofei.aop.annotation;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

//声明一个IOC容器的bean
@Component
//声明是一个切面 ，可以集中声明横切功能的声明， 比如日志功能 ， 验证参数的切面
@Aspect
@Order(1)
public class LoggingAspect {
    /*    */

    /**
     * @Before 就是一个前置通知
     * apsect 表达式， execution开头
     * public 代表方法级别
     * int 方法返回值
     * add 表示某个包下的方法
     * int int 代表参数列表
     *//*
    @Before(value = "execution(public int com.mofei.aop.annotation.ArithmeticCalculatorImpl.*(int ,int))")
    public void before(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        List<Object> objects = Arrays.asList(args);
        System.out.println(" 执行 " + methodName + " 方法之前，参数是 " + objects);
    }

    @After(value = "execution(public int com.mofei.aop.annotation.ArithmeticCalculatorImpl.*(int ,int))")
    public void after(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        System.out.println(" 执行 " + methodName + " 方法之后");
    }

    @AfterReturning(value = "execution(public int com.mofei.aop.annotation.ArithmeticCalculatorImpl.*(int ,int))", returning = "result")
    public void afterReturning(JoinPoint joinPoint, Object result) {
        String methodName = joinPoint.getSignature().getName();
        System.out.println(" 执行 " + methodName + " 方法之后,结果是result = " + result);
    }

    @AfterThrowing(value = "execution(public int com.mofei.aop.annotation.ArithmeticCalculatorImpl.*(int ,int))", throwing = "ex")
    public void afterThrowing(JoinPoint joinPoint, Exception ex) {
        String methodName = joinPoint.getSignature().getName();
        System.out.println(" 执行 " + methodName + " 方法失败之后,结果是result = " + ex);
    }*/
    @Around(value = "execution(*  com.mofei.aop.annotation.ArithmeticCalculator.*(..))")
    public Object around(ProceedingJoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        List<Object> objects = Arrays.asList(args);
        System.out.println(" 执行 " + methodName + " 方法之前，参数是 " + objects);
        Object result = null;
        try {
            result = joinPoint.proceed(args);
        } catch (Throwable throwable) {
            System.out.println(" 执行 " + methodName + " 方法失败之后,结果是result = " + throwable);
        }
        System.out.println(" 执行 " + methodName + " 方法之后,结果是result = " + result);
        return result;
    }
}
