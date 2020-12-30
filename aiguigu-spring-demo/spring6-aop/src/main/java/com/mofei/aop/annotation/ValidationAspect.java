package com.mofei.aop.annotation;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
@Aspect
@Order(2)
public class ValidationAspect {


    @Before(value = "execution(* com.mofei.aop.annotation.ArithmeticCalculator.*(..))")
    public void before(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        System.out.println("正在执行方法" + methodName + "的参数校验");
        Object[] args = joinPoint.getArgs();
        List<Object> objects = Arrays.asList(args);
        for (int i = 0; i < objects.size(); i++) {
            int param = (int) objects.get(i);
            if (param < 0) {
                System.out.println("参数：" + param + "非法");
            }
        }
    }
}
